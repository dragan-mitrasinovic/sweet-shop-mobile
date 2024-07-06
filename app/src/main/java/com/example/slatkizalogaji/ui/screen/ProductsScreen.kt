package com.example.slatkizalogaji.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.data.Product
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.fancyTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun ProductsScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    products: MutableList<Product>,
    navigateToProductPage: (Product) -> Unit
) {
    val scrollState = rememberScrollState()
    val productsToShow = remember { mutableStateOf(products.toList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        SweetShopAppBar(appBarOpened, navController)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { productsToShow.value = products },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White.copy(alpha = 0.253f),
                ),
                border = BorderStroke(0.3.dp, buttonBorderColor),
            ) {
                Text(
                    text = "Svi proizvodi",
                    style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                )
            }

            Button(
                onClick = {
                    productsToShow.value = products.filter { it.type == "cake" }.toMutableList()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White.copy(alpha = 0.253f),
                ),
                border = BorderStroke(0.3.dp, buttonBorderColor),
            ) {
                Text(
                    text = "Torte",
                    style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                )
            }

            Button(
                onClick = {
                    productsToShow.value = products.filter { it.type == "cupcake" }.toMutableList()
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.White.copy(alpha = 0.253f),
                ),
                border = BorderStroke(0.3.dp, buttonBorderColor)
            ) {
                Text(
                    text = "Kolači",
                    style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                )
            }
        }

        for (product in productsToShow.value) {
            ProductCard(product, navigateToProductPage)
        }
    }
}

@Composable
fun ProductCard(product: Product, navigateToProductPage: (Product) -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier.padding(horizontal = 36.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(gradient)
                    .fillMaxWidth()

            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.name,
                    style = fancyTextStyle.copy(fontSize = 48.sp, color = Color.White),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = "Product image",
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { navigateToProductPage(product) }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}