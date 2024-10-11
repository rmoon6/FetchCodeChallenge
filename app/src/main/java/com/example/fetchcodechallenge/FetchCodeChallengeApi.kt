package com.example.fetchcodechallenge

import retrofit2.Retrofit
import retrofit2.http.GET

interface FetchCodeChallengeApi {

    @GET("hiring.json")
    suspend fun getItems(): List<FetchListItem>

    companion object {
        fun createInstance(retrofit: Retrofit): FetchCodeChallengeApi {
            return retrofit.create(FetchCodeChallengeApi::class.java)
        }
    }
}
