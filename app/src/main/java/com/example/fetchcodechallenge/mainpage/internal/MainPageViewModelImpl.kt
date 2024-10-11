package com.example.fetchcodechallenge.mainpage.internal

import androidx.lifecycle.viewModelScope
import com.example.fetchcodechallenge.api.FetchCodeChallengeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class MainPageViewModelImpl(
    private val api: FetchCodeChallengeApi
) : MainPageViewModel() {

    override val state = MutableStateFlow<MainPageState>(MainPageState.Loading)

    init {
        viewModelScope.launch {
            val items = api
                .getItems()
                .filterNot { it.name.isNullOrBlank() }
            state.value = MainPageState.WithItems(items)
        }
    }
}
