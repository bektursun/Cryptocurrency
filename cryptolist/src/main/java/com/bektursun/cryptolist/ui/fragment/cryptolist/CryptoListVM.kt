package com.bektursun.cryptolist.ui.fragment.cryptolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bektursun.core.viewmodel.CoreViewModel
import com.bektursun.cryptolist.repository.CryptoListRepository
import com.bektursun.data.currencyTicker.CurrencyTicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class CryptoListVM : CoreViewModel() {

    abstract val currenciesTicker: LiveData<List<CurrencyTicker>>

    abstract suspend fun fetchCurrenciesTickerRemotely(
        identificators: String,
        interval: String? = null,
        convert: String? = null,
        pageSize: Int? = null,
        pageNumber: Int? = null
    ): List<CurrencyTicker>

    abstract fun searchCurrencies(query: String?)
}

class CryptoListVMImpl(private val repository: CryptoListRepository) : CryptoListVM() {

    override val currenciesTicker: LiveData<List<CurrencyTicker>> get() = repository.fetchCurrencyTickerLocally()

    override suspend fun fetchCurrenciesTickerRemotely(
        identificators: String,
        interval: String?,
        convert: String?,
        pageSize: Int?,
        pageNumber: Int?
    ) = repository.fetchCurrencyTickerRemotely(
        identificators, interval, convert, pageSize, pageNumber
    )

    private fun saveInCache(currencies: List<CurrencyTicker>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCurrencyTicker(currencies)
        }
    }

    override fun searchCurrencies(query: String?) {
        viewModelScope.launch {
            if (query != null) {
                val response = repository.fetchCurrencyTickerRemotely(query)
                saveInCache(response)
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE: Int = 30
        private const val DEFAULT_PAGE_NUMBER: Int = 0
        private const val DEFAULT_CONVERT_CURRENCY: String = "USD"
    }

}