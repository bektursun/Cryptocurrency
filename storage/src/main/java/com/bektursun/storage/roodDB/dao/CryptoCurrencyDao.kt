package com.bektursun.storage.roodDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bektursun.data.currencyTicker.CurrencyTicker
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM currencyTicker")
    fun fetchCurrencyTicker(): Flow<CurrencyTicker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyTicker(currencyTicker: CurrencyTicker)

}