package com.bektursun.cryptolist.ui.fragment.cryptolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bektursun.core.utils.ResultWrapper
import com.bektursun.core.viewmodel.CoreViewModel
import com.bektursun.cryptolist.repository.CryptoListRepository
import com.bektursun.data.currencyTicker.CurrencyTicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

abstract class CryptoListVM : CoreViewModel() {

    abstract val currenciesTicker: LiveData<List<CurrencyTicker>>

    abstract val currenciesTickerState: LiveData<ResultWrapper<String>>

    abstract fun searchCurrencies(
        query: String?, interval: String? = null, convert: String? = null,
        pageSize: Int? = null, pageNumber: Int? = null
    )
}

class CryptoListVMImpl(private val repository: CryptoListRepository) : CryptoListVM() {

    override val currenciesTicker: LiveData<List<CurrencyTicker>> =
        repository.fetchCurrencyTickerLocally().asLiveData()

    private val _currenciesTickerState: MutableLiveData<ResultWrapper<String>> = MutableLiveData()
    override val currenciesTickerState: LiveData<ResultWrapper<String>> get() = _currenciesTickerState

    private fun saveInCache(currencies: List<CurrencyTicker>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCurrencyTicker(currencies)
        }
    }

    override fun searchCurrencies(
        query: String?, interval: String?, convert: String?,
        pageSize: Int?, pageNumber: Int?
    ) {
        viewModelScope.launch {
            try {
                if (query != null && query.isNotEmpty()) {
                    _currenciesTickerState.value = ResultWrapper.loading()
                    val response =
                        repository.fetchCurrencyTickerRemotely(
                            query.toUpperCase(Locale.getDefault()),
                            interval,
                            convert,
                            DEFAULT_PAGE_SIZE,
                            pageNumber
                        )

                    if (response.isNotEmpty()) {
                        saveInCache(response)
                        _currenciesTickerState.value = ResultWrapper.success("$query")
                    } else _currenciesTickerState.value = ResultWrapper.empty("$query")
                }
            } catch (t: Throwable) {
                _currenciesTickerState.value = ResultWrapper.error(t)
                t.printStackTrace()
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE: Int = 30
        private const val DEFAULT_PAGE_NUMBER: Int = 0
        private const val DEFAULT_CONVERT_CURRENCY: String = "USD"
    }

}