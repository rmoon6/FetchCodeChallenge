package com.example.fetchcodechallenge.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface FetchCodeChallengeApi {

    @GET("hiring.json")
    suspend fun getItems(): List<FetchListItem>

    companion object {

        private const val BASE_API_URL = "https://fetch-hiring.s3.amazonaws.com/"

        fun createInstance(): FetchCodeChallengeApi {
            val retrofit = createRetrofit(
                okHttpClient = createOkHttpClient(),
                moshi = createMoshi()
            )
            return retrofit.create(FetchCodeChallengeApi::class.java)
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
}
