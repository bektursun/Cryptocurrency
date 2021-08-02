package com.bektursun.network.data.api

import com.bektursun.data.currencyTicker.CurrencyTicker
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("currencies/ticker")
    suspend fun fetchCurrenciesTicker(
        @Query("ids") currencyIdentificators: String,
        @Query("interval") interval: String? = null,
        @Query("convert") convert: String? = null,
        @Query("per-page") pageSize: Int? = null,
        @Query("page") pageNumber: Int? = null
    ): List<CurrencyTicker>

}

