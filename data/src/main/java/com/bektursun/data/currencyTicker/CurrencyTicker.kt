package com.bektursun.data.currencyTicker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencyTicker")
data class CurrencyTicker(
    @PrimaryKey
    val id : String,
    val currency : String,
    val symbol : String,
    val name : String,
    val logo_url : String,
    val status : String,
    val price : Double
)