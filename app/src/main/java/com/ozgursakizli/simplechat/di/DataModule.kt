package com.ozgursakizli.simplechat.di

import com.ozgursakizli.simplechat.BuildConfig
import com.ozgursakizli.simplechat.network.ApiService
import com.ozgursakizli.simplechat.repositories.ChatRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { provideApiService(get()) }
    single { provideRetrofit() }

    // region Repositories
    single { ChatRepository(get()) }
    // endregion
}

fun provideRetrofit(): Retrofit {
    val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)