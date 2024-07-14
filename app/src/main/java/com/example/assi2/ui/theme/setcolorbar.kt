package com.example.assi2.ui.theme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color

class Setcolorbar() {
    @Composable
    fun SetStatusBarColor(color:Color ) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color=color)
        }
    }}

