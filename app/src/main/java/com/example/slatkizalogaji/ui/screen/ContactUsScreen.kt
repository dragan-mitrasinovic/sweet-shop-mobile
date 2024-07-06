package com.example.slatkizalogaji.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.slatkizalogaji.R
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun ContactUsScreen(navController: NavController, appBarOpened: MutableState<Boolean>) {
    Column(modifier = Modifier.fillMaxSize()) {
        SweetShopAppBar(appBarOpened, navController)

        Spacer(modifier = Modifier.height(200.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            Column(modifier = Modifier.background(gradient)) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Kontakt",
                    style = regularTextStyle.copy(fontSize = 30.sp, color = Color.White),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.padding(start = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        Canvas(modifier = Modifier.size(44.dp)) {
                            drawRoundRect(
                                color = purple.copy(alpha = 0.7f),
                                cornerRadius = CornerRadius(24f, 24f)
                            )
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "Phone",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Text(
                        text = "+381 11 123 456",
                        style = regularTextStyle.copy(fontSize = 22.sp, color = Color.White),
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.padding(start = 32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        Canvas(modifier = Modifier.size(44.dp)) {
                            drawRoundRect(
                                color = purple.copy(alpha = 0.7f),
                                cornerRadius = CornerRadius(24f, 24f)
                            )
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "Phone",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Text(
                        text = "slatkizalogaji@gmail.com",
                        style = regularTextStyle.copy(fontSize = 22.sp, color = Color.White),
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactUsScreenPreview() {
    ContactUsScreen(
        navController = rememberNavController(),
        appBarOpened = remember { mutableStateOf(false) }
    )
}