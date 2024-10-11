package com.example.fetchcodechallenge.mainpage.internal

import com.example.fetchcodechallenge.FetchCodeChallengeApi
import kotlinx.coroutines.flow.MutableStateFlow

internal class FetchListViewModelImpl(
    private val api: FetchCodeChallengeApi
) : FetchListViewModel() {

    override val state = MutableStateFlow<FetchListState>(FetchListState.Loading)

    init {
        // TODO STOPSHIP load the items!!
    }
}
