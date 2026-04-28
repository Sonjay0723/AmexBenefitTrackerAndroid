package com.example.amexbenefittracker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usage_history")
data class UsageHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val benefitId: Long,
    val amountClaimed: Double,
    val dateClaimed: Long,
    val periodIdentifier: String // e.g., "2026-01", "2026-H1", "2026-Annual"
)
