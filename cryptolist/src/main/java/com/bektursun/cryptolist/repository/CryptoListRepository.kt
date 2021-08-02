package com.bektursun.cryptolist.repository

import com.bektursun.data.currencyTicker.CurrencyTicker
import com.bektursun.network.data.api.CurrencyApi
import com.bektursun.storage.roomDB.dao.CryptoCurrencyDao
import kotlinx.coroutines.flow.Flow

interface CryptoListRepository {

    fun fetchCurrencyTickerLocally(): Flow<List<CurrencyTicker>>

    suspend fun fetchCurrencyTickerRemotely(
        identificators: String,
        interval: String? = null,
        convert: String? = null,
        pageSize: Int? = null,
        pageNumber: Int? = null
    ): List<CurrencyTicker>

    suspend fun insertCurrencyTicker(currencies: List<CurrencyTicker>)

}

class CryptoListRepositoryImpl(private val api: CurrencyApi, private val dao: CryptoCurrencyDao) :
    CryptoListRepository {

    override fun fetchCurrencyTickerLocally(): Flow<List<CurrencyTicker>> =
        dao.fetchCurrencyTicker()

    override suspend fun fetchCurrencyTickerRemotely(
        identificators: String,
        interval: String?,
        convert: String?,
        pageSize: Int?,
        pageNumber: Int?
    ): List<CurrencyTicker> = api.fetchCurrenciesTicker(
        identificators,
        interval,
        convert,
        pageSize,
        pageNumber
    )

    override suspend fun insertCurrencyTicker(currencies: List<CurrencyTicker>) {
        try {
            deleteCurrenciesFromDB()
            dao.insertCurrencyTicker(currencies)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    // clear table before insert new data
    private suspend fun deleteCurrenciesFromDB() {
        dao.deleteCurrencies()
    }

}