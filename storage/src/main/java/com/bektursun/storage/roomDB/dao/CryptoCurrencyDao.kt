package com.bektursun.storage.roomDB.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bektursun.data.currencyTicker.CurrencyTicker

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM currencyTicker")
    fun fetchCurrencyTicker(): LiveData<List<CurrencyTicker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyTicker(currencyTicker: List<CurrencyTicker>)

    @Query("DELETE FROM currencyTicker")
    suspend fun deleteCurrencies()

}