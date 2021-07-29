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
    val price : Double,
    val price_date : String,
    val price_timestamp : String,
    val circulating_supply : Int,
    val max_supply : Int,
    val market_cap : Int,
    val market_cap_dominance : Double,
    val num_exchanges : Int,
    val num_pairs : Int,
    val num_pairs_unmapped : Int,
    val first_candle : String,
    val first_trade : String,
    val first_order_book : String,
    val rank : Int,
    val rank_delta : Int,
    val high : Double,
    val high_timestamp : String
)