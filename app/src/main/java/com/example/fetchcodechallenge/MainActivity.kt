package com.example.fetchcodechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.fetchcodechallenge.mainpage.MainPage
import com.example.fetchcodechallenge.theme.FetchCodeChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchCodeChallengeTheme { MainPage(Modifier.fillMaxSize()) }
        }
    }
}
