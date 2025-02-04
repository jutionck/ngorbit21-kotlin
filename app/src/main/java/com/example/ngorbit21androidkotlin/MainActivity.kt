package com.example.ngorbit21androidkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.ngorbit21androidkotlin.presentation.navigation.NavGraph
import com.example.ngorbit21androidkotlin.presentation.ui.theme.NgorbIT21AndroidKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NgorbIT21AndroidKotlinTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}