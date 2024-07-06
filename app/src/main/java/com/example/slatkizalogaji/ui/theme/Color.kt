package com.example.slatkizalogaji.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val purple = Color(0xFF88276D)

val buttonBorderColor = Color(0x73ffffff)
val gradient = Brush.linearGradient(
    colors = listOf(
        Color(0x39ffffff),
        Color(0x1d4fd089),
        Color(0x3b4fd089),
        Color(0x2088276c),
        Color(0x4988276c)
    ),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)