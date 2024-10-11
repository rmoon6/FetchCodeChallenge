package com.example.fetchcodechallenge.mainpage.internal

import com.example.fetchcodechallenge.FetchListItem

internal sealed interface FetchListState {
    object Loading : FetchListState
    data class WithItems(val items: List<FetchListItem>) : FetchListState
    object NetworkError : FetchListState
}
