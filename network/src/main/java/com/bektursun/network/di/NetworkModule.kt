package com.bektursun.network.di

import com.bektursun.network.BuildConfig
import com.bektursun.network.data.api.CurrencyApi
import com.bektursun.network.interceptors.ApiKeyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single { get<Retrofit>().create(CurrencyApi::class.java) }
}

fun provideOkHttp(): OkHttpClient {
    val builder = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor())

    if (BuildConfig.DEBUG) {
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
    return builder.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()