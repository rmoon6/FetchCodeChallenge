package com.example.fetchcodechallenge.mainpage.internal

import androidx.lifecycle.viewModelScope
import com.example.fetchcodechallenge.api.FetchCodeChallengeApi
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.Loading
import com.example.fetchcodechallenge.mainpage.internal.MainPageState.WithItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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

    private suspend fun refreshListItems() {
        runCatching {
            val items = api
                .getItems()
                .filterNot { it.name.isNullOrBlank() }
                .sortedWith(compareBy(
                    { it.listId },
                    { it.name }
                ))
            state.value = WithItems(items)
        }.onFailure {
            when (it) {
                is IOException, is HttpException -> state.value = MainPageState.NetworkError
                else -> throw it
            }
        }
    }
}
