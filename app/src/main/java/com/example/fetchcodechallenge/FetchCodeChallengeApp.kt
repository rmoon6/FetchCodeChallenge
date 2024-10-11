package com.example.fetchcodechallenge

import android.app.Application
import com.example.fetchcodechallenge.api.FetchCodeChallengeApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FetchCodeChallengeApp : Application() {

    companion object {
        private const val BASE_API_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }

    lateinit var moshi: Moshi
    lateinit var api: FetchCodeChallengeApi

    override fun onCreate() {
        super.onCreate()
        moshi = createMoshi()

        val retrofit = createRetrofit(okHttpClient = createOkHttpClient(), moshi = moshi)
        api = FetchCodeChallengeApi.createInstance(retrofit)
    }

    private fun createMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}

