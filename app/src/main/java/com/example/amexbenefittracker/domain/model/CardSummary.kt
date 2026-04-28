package com.example.amexbenefittracker.domain.model

data class CardSummary(
    val cardId: Long,
    val cardName: String,
    val standardAnnualFee: Double,
    val corporateCredit: Double,
    val corporateCreditClaimed: Boolean,
    val totalBenefitsClaimed: Double,
    val effectiveAnnualFee: Double,
    val profit: Double
)
