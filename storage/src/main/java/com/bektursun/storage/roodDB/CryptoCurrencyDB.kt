package com.bektursun.storage.roodDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bektursun.data.currencyTicker.CurrencyTicker
import com.bektursun.storage.roodDB.dao.CryptoCurrencyDao

@Database(entities = [CurrencyTicker::class], version = 1, exportSchema = false)
abstract class CryptoCurrencyDB : RoomDatabase() {

    abstract fun getCurrencyDao(): CryptoCurrencyDao

    companion object {
        private const val DB_NAME: String = "cryptoDB"
        private var instance: CryptoCurrencyDB? = null

        fun getInstance(context: Context): CryptoCurrencyDB {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CryptoCurrencyDB::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}