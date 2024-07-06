package com.example.slatkizalogaji.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.slatkizalogaji.R

val Merinda = FontFamily(
    Font(R.font.merinda_normal, FontWeight.Normal, FontStyle.Normal),
)

val PuppiesPlay = FontFamily(
    Font(R.font.puppies_play_normal, FontWeight.Normal, FontStyle.Normal),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val regularTextStyle = TextStyle(
    fontFamily = Merinda,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
)

val fancyTextStyle = TextStyle(
    fontFamily = PuppiesPlay,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
)

val errorTextStyle = TextStyle(
    fontFamily = Merinda,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontSize = 14.sp,
    color = Color(0xff992121)
)
