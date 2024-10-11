package com.example.fetchcodechallenge.mainpage.internal

import com.example.fetchcodechallenge.FetchListItem

internal sealed interface MainPageState {
    object Loading : MainPageState
    data class WithItems(val items: List<FetchListItem>) : MainPageState
    object NetworkError : MainPageState
}
