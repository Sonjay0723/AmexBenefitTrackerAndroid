package com.example.amexbenefittracker.ui.dashboard

import com.example.amexbenefittracker.data.local.entities.Benefit
import com.example.amexbenefittracker.data.local.entities.UsageHistory

data class BenefitUiModel(
    val benefit: Benefit,
    val totalClaimedInPeriod: Double,
    val isClaimedInCurrentPeriod: Boolean,
    val progress: Float,
    val history: List<UsageHistory>
)
