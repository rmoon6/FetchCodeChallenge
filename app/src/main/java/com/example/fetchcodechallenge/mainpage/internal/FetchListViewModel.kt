package com.example.fetchcodechallenge.mainpage.internal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

internal abstract class FetchListViewModel : ViewModel() {
    abstract val state: StateFlow<FetchListState>
}
