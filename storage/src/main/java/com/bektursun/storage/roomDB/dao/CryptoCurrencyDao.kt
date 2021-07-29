package com.bektursun.storage.roomDB.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bektursun.data.currencyTicker.CurrencyTicker

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM currencyTicker")
    fun fetchCurrencyTicker(): LiveData<List<CurrencyTicker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyTicker(currencyTicker: List<CurrencyTicker>)

}