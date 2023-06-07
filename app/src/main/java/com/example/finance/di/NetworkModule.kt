package com.example.finance.di

import com.example.finance.data.response.rub.RubApi
import com.example.finance.data.response.user.UserApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()

    @Named("rub")
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(gsonFactory)
        .baseUrl("https://www.cbr-xml-daily.ru/daily_json.js/")
        .build()

    @Provides
    fun provideFinanceApi(
        @Named("rub")
        retrofit: Retrofit
    ): RubApi = retrofit.create(RubApi::class.java)

    //TODO
    @Named("user")
    @Provides
    fun provideUserRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .addConverterFactory(gsonFactory)
        .baseUrl("http://localhost/")
        .build()

    @Provides
    fun provideUserApi(
        @Named("user")
        retrofit: Retrofit
    ): UserApi = retrofit.create(UserApi::class.java)

}
