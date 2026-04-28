package com.example.amexbenefittracker.data.repository

import com.example.amexbenefittracker.data.local.dao.BenefitDao
import com.example.amexbenefittracker.data.local.dao.CardDao
import com.example.amexbenefittracker.data.local.dao.UsageHistoryDao
import com.example.amexbenefittracker.data.local.entities.Benefit
import com.example.amexbenefittracker.data.local.entities.BenefitType
import com.example.amexbenefittracker.data.local.entities.Card
import com.example.amexbenefittracker.data.local.entities.UsageHistory
import com.example.amexbenefittracker.domain.model.CardSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class BenefitRepository(
    private val cardDao: CardDao,
    private val benefitDao: BenefitDao,
    private val usageHistoryDao: UsageHistoryDao
) {
    fun getAllCards(): Flow<List<Card>> = cardDao.getAllCards()

    fun getBenefitsForCard(cardId: Long): Flow<List<Benefit>> = benefitDao.getBenefitsForCard(cardId)

    fun getUsageForCard(cardId: Long): Flow<List<UsageHistory>> = usageHistoryDao.getUsageForCard(cardId)

    fun getCardSummary(cardId: Long): Flow<CardSummary?> {
        val cardFlow = cardDao.getAllCards().map { cards -> cards.find { it.id == cardId } }
        val usageFlow = usageHistoryDao.getUsageForCard(cardId)

        return combine(cardFlow, usageFlow) { card, usages ->
            if (card == null) return@combine null

            val totalClaimed = usages.sumOf { it.amountClaimed }
            val corpCreditValue = if (card.corporateCreditClaimed) card.corporateCredit else 0.0
            val effectiveFee = card.annualFee - corpCreditValue - totalClaimed
            val profit = if (effectiveFee < 0) -effectiveFee else 0.0

            CardSummary(
                cardId = card.id,
                cardName = card.name,
                standardAnnualFee = card.annualFee,
                corporateCredit = card.corporateCredit,
                corporateCreditClaimed = card.corporateCreditClaimed,
                totalBenefitsClaimed = totalClaimed,
                effectiveAnnualFee = effectiveFee,
                profit = profit
            )
        }
    }

    suspend fun toggleUsage(benefit: Benefit, periodIdentifier: String, date: Long) {
        val existing = usageHistoryDao.getUsageForPeriod(benefit.id, periodIdentifier)
        if (existing != null) {
            usageHistoryDao.deleteUsage(benefit.id, periodIdentifier)
            
            // Link Uber Cash logic
            if (benefit.name == "Uber Cash") {
                val otherUberBenefits = benefitDao.getBenefitsByName("Uber Cash")
                otherUberBenefits.forEach { other ->
                    if (other.id != benefit.id) {
                        usageHistoryDao.deleteUsage(other.id, periodIdentifier)
                    }
                }
            }
        } else {
            val amount = when (benefit.type) {
                BenefitType.MONTHLY -> {
                    if (periodIdentifier.endsWith("-12")) benefit.decemberValue else benefit.monthlyValue
                }
                BenefitType.QUARTERLY -> benefit.quarterlyValue
                BenefitType.SEMI_ANNUAL -> benefit.semiAnnualValue
                BenefitType.ANNUAL -> benefit.totalValue
            }
            val usage = UsageHistory(
                benefitId = benefit.id,
                amountClaimed = amount,
                dateClaimed = date,
                periodIdentifier = periodIdentifier
            )
            usageHistoryDao.insertUsage(usage)

            // Link Uber Cash logic
            if (benefit.name == "Uber Cash") {
                val otherUberBenefits = benefitDao.getBenefitsByName("Uber Cash")
                otherUberBenefits.forEach { other ->
                    if (other.id != benefit.id) {
                        val otherAmount = if (periodIdentifier.endsWith("-12")) other.decemberValue else other.monthlyValue
                        usageHistoryDao.insertUsage(
                            UsageHistory(
                                benefitId = other.id,
                                amountClaimed = otherAmount,
                                dateClaimed = date,
                                periodIdentifier = periodIdentifier
                            )
                        )
                    }
                }
            }
        }
    }

    suspend fun toggleCorporateCredit(cardId: Long) {
        val card = cardDao.getCardById(cardId)
        if (card != null) {
            cardDao.updateCard(card.copy(corporateCreditClaimed = !card.corporateCreditClaimed))
        }
    }

    suspend fun resetAllTracking() {
        usageHistoryDao.deleteAllUsage()
        val cards = cardDao.getAllCards().first()
        cards.forEach { card ->
            cardDao.updateCard(card.copy(corporateCreditClaimed = false))
        }
    }

    suspend fun insertCard(card: Card): Long = cardDao.insertCard(card)
    suspend fun insertBenefit(benefit: Benefit): Long = benefitDao.insertBenefit(benefit)
}
