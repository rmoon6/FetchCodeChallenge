package com.example.fetchcodechallenge.mainpage.internal

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.fetchcodechallenge.FetchCodeChallengeApp

// TODO STOPSHIP put this somewhere else!!
val CreationExtras.fetchApp: FetchCodeChallengeApp
    get() = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FetchCodeChallengeApp

