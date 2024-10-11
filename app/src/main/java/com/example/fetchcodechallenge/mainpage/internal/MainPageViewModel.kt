package com.example.fetchcodechallenge.mainpage.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.StateFlow

internal abstract class MainPageViewModel : ViewModel() {

    abstract val state: StateFlow<MainPageState>

    abstract fun onNetworkErrorRetry()

    object CreationFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return MainPageViewModelImpl(extras.fetchApp.api) as T
        }
    }
}
