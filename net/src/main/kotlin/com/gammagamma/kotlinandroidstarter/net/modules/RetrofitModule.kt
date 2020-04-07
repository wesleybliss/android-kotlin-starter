package com.gammagamma.kotlinandroidstarter.net.modules

import com.gammagamma.kotlinandroidstarter.net.BuildConfig
import com.gammagamma.kotlinandroidstarter.net.service.ApiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val RetrofitModule = module {
    
    single { buildRetrofit(get(), get()) }
    single { buildApiService(get()) }
    
}

private fun buildRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .build()
    
private fun buildApiService(retrofit: Retrofit) =
    retrofit.create(ApiService::class.java)
