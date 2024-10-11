package com.example.fetchcodechallenge

import android.app.Application
import com.example.fetchcodechallenge.api.FetchCodeChallengeApi

class FetchCodeChallengeApp : Application() {

    lateinit var api: FetchCodeChallengeApi

    override fun onCreate() {
        super.onCreate()
        api = FetchCodeChallengeApi.createInstance()
    }
}
