package com.example.slatkizalogaji.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.data.Item
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.fancyTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun OrderScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    items: List<Item>,
    goToProductDetailPage: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SweetShopAppBar(appBarOpened, navController)

        Spacer(modifier = Modifier.height(40.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(gradient)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Porudžbina",
                    style = regularTextStyle.copy(fontSize = 36.sp, color = Color.White),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)


                OrderCart(items, goToProductDetailPage)

                Row(
                    modifier = Modifier.height(80.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "Ukupno: " + items.sumOf { it.product.price * it.count } + " RSD",
                        style = fancyTextStyle.copy(fontSize = 40.sp, color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.width(40.dp))
                }
            }
        }
    }
}

@Composable
fun OrderCart(
    items: List<Item>,
    goToProductDetailPage: (String) -> Unit
) {
    for (item in items) {
        if (items.any { it.product.name == item.product.name }) {
            OrderCartItem(item, items, goToProductDetailPage)

            HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
        }
    }
}

@Composable
fun OrderCartItem(
    item: Item,
    items: List<Item>,
    goToProductDetailPage: (String) -> Unit
) {
    val quantity = remember { mutableIntStateOf(item.count) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(78.dp)
    ) {
        Image(
            painter = painterResource(id = item.product.image),
            contentDescription = "Product Image",
            modifier = Modifier
                .padding(10.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { goToProductDetailPage(item.product.name) }
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text(
                text = item.product.name,
                style = fancyTextStyle.copy(fontSize = 30.sp, color = Color.White)
            )

            Text(
                text = (item.product.price * item.count).toString() + " RSD",
                style = fancyTextStyle.copy(fontSize = 30.sp, color = Color.White)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        VerticalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)

        Spacer(modifier = Modifier.width(30.dp))

        TextField(
            value = quantity.intValue.toString(),
            onValueChange = {},
            colors = TextFieldDefaults.colors().copy(
                focusedTextColor = purple,
                unfocusedTextColor = purple,
                cursorColor = purple,
                focusedContainerColor = Color.White.copy(alpha = 0.5f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.5f),

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                disabledTextColor = purple,
                disabledContainerColor = Color.White.copy(alpha = 0.5f),
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = regularTextStyle.copy(fontSize = 10.sp),
            enabled = false,
            modifier = Modifier
                .height(44.dp)
                .width(44.dp)
                .padding(horizontal = 4.dp)
        )
    }
}

