package com.demo.marvel.shared.di.module

import com.demo.marvel.network.ApiService
import com.demo.marvel.shared.util.Constants.TIMEOUT
import com.demo.marvel.shared.util.NetworkUtils.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkModule {
        @Provides
        fun providesRetrofit(): Retrofit {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(loggingInterceptor)
            okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS)
            okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            val gson = GsonBuilder().create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @Provides
        fun providesService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
