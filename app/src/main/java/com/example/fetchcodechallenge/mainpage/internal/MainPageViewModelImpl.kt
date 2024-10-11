package com.example.fetchcodechallenge.mainpage.internal

import androidx.lifecycle.viewModelScope
import com.example.fetchcodechallenge.api.FetchCodeChallengeApi
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.Loading
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.WithItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class MainPageViewModelImpl(
    private val api: FetchCodeChallengeApi
) : MainPageViewModel() {

    override val state = MutableStateFlow<MainPageState>(Loading)

    init {
        viewModelScope.launch { refreshListItems() }
    }

    override fun onNetworkErrorRetry() {
        state.value = Loading
        viewModelScope.launch { refreshListItems() }
    }

    // TODO STOPSHIP handle exceptions somehow!!
    private suspend fun refreshListItems() {
        val items = api
            .getItems()
            .filterNot { it.name.isNullOrBlank() }
            .sortedWith(compareBy(
                { it.listId },
                { it.name }
            ))
        state.value = WithItems(items)
    }
}
