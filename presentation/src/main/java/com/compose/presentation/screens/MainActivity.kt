package com.compose.presentation.screens

import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.compose.presentation.composable.NavGraph
import com.compose.presentation.theme.composeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        window.insetsController?.hide(WindowInsets.Type.statusBars())
        setContent {
            composeTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
