package com.bektursun.cryptolist.repository

import androidx.lifecycle.LiveData
import com.bektursun.data.currencyTicker.CurrencyTicker
import com.bektursun.network.data.api.CurrencyApi
import com.bektursun.storage.roomDB.dao.CryptoCurrencyDao

interface CryptoListRepository {

    fun fetchCurrencyTickerLocally(): LiveData<List<CurrencyTicker>>

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

    override fun fetchCurrencyTickerLocally() = dao.fetchCurrencyTicker()

    override suspend fun fetchCurrencyTickerRemotely(
        identificators: String,
        interval: String?,
        convert: String?,
        pageSize: Int?,
        pageNumber: Int?
    ): List<CurrencyTicker> =
        api.fetchCurrenciesTicker(identificators, interval, convert, pageSize, pageNumber)

    override suspend fun insertCurrencyTicker(currencies: List<CurrencyTicker>) {
        dao.insertCurrencyTicker(currencies)
    }

}