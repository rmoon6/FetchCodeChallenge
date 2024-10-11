package com.example.fetchcodechallenge.mainpage.internal

import com.example.fetchcodechallenge.FetchCodeChallengeApi
import kotlinx.coroutines.flow.MutableStateFlow

internal class MainPageViewModelImpl(
    private val api: FetchCodeChallengeApi
) : MainPageViewModel() {

    override val state = MutableStateFlow<MainPageState>(MainPageState.Loading)

    init {
        // TODO STOPSHIP load the items!!
    }
}
