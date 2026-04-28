package com.example.amexbenefittracker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class BenefitType {
    MONTHLY,
    QUARTERLY,
    SEMI_ANNUAL,
    ANNUAL
}

@Entity(tableName = "benefits")
data class Benefit(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cardId: Long,
    val name: String,
    val description: String,
    val totalValue: Double,
    val type: BenefitType,
    val monthlyValue: Double = 0.0,
    val decemberValue: Double = 0.0,
    val quarterlyValue: Double = 0.0,
    val semiAnnualValue: Double = 0.0,
    val displayOrder: Int = 0
)
