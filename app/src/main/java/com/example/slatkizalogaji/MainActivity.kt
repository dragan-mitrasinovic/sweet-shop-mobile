package com.example.slatkizalogaji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.slatkizalogaji.ui.SweetShopApp
import com.example.slatkizalogaji.ui.theme.SlatkiZalogajiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlatkiZalogajiTheme {
                SweetShopApp()
            }
        }
    }
}
