package com.example.amexbenefittracker.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val annualFee: Double,
    val corporateCredit: Double = 0.0,
    val corporateCreditClaimed: Boolean = false,
    val isDefault: Boolean = false
)
