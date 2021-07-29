package com.bektursun.network.data.api

import com.bektursun.data.currencyTicker.CurrencyTicker
import com.bektursun.network.constants.Intervals
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("currencies/ticker")
    suspend fun fetchCurrenciesTicker(
        @Query("ids") currencyIdentificators: String,
        @Query("interval") interval: String = Intervals.fetchDefaultInterval(),
        @Query("convert") convert: String = DEFAULT_CONVERT_CURRENCY,
        @Query("per-page") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("page") pageNumber: Int = DEFAULT_PAGE_NUMBER
    ): List<CurrencyTicker>

    companion object {
        private const val DEFAULT_PAGE_SIZE: Int = 30
        private const val DEFAULT_PAGE_NUMBER: Int = 0
        private const val DEFAULT_CONVERT_CURRENCY: String = "USD"
    }

}

