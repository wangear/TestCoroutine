package com.toy.loginwork.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.toy.loginwork.repository.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitService(gson: Gson, okHttpClient: OkHttpClient) : ApiInterface{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://35.200.92.60:11000")
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun getOkHttpClient() : OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}