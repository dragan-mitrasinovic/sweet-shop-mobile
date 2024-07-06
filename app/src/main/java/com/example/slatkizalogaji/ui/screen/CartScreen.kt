package com.example.slatkizalogaji.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.R
import com.example.slatkizalogaji.data.Item
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.fancyTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun CartScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    items: MutableState<List<Item>>,
    setProductQuantity: (String, Int) -> Unit,
    goToProductDetailPage: (String) -> Unit,
    completeOrder: () -> Unit
) {
    val context = LocalContext.current

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
                if (items.value.isEmpty()) {
                    Text(
                        text = "Korpa je prazna",
                        style = regularTextStyle.copy(fontSize = 24.sp, color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )
                } else {
                    Text(
                        text = "Korpa",
                        style = regularTextStyle.copy(fontSize = 36.sp, color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )

                    HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
                }

                Cart(items, setProductQuantity, goToProductDetailPage)

                Row(
                    modifier = Modifier.height(80.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "Ukupno: " + items.value.sumOf { it.product.price * it.count } + " RSD",
                        style = fancyTextStyle.copy(fontSize = 40.sp, color = Color.White),
                    )

                    Spacer(modifier = Modifier.width(40.dp))

                    Button(
                        onClick = {
                            completeOrder()

                            Toast.makeText(
                                context,
                                "Proizvod dodat u korpu",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Green.copy(alpha = 0.15f),
                        ),
                        border = BorderStroke(0.3.dp, buttonBorderColor),
                        enabled = items.value.isNotEmpty(),
                    ) {
                        Text(
                            text = "Poruči",
                            style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Cart(
    items: MutableState<List<Item>>,
    setProductQuantity: (String, Int) -> Unit,
    goToProductDetailPage: (String) -> Unit
) {
    for (item in items.value) {
        if (items.value.any { it.product.name == item.product.name }) {
            CartItem(item, items, setProductQuantity, goToProductDetailPage)

            HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
        }
    }
}

@Composable
fun CartItem(
    item: Item,
    items: MutableState<List<Item>>,
    setProductQuantity: (String, Int) -> Unit,
    goToProductDetailPage: (String) -> Unit
) {
    val quantity = remember { mutableIntStateOf(item.count) }
    val quantityText = remember { mutableStateOf(item.count.toString()) }

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

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(
            onClick = {
                setProductQuantity(item.product.name, quantity.intValue + 1)
                quantity.intValue = item.count
                quantityText.value = item.count.toString()
            },
            modifier = Modifier
                .height(24.dp)
                .width(24.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Add",
                tint = Color.White
            )
        }

        TextField(
            value = quantityText.value,
            onValueChange = {
                quantityText.value = it
                if (it.isNotEmpty()) {
                    setProductQuantity(item.product.name, it.toInt())
                }
            },
            colors = TextFieldDefaults.colors().copy(
                focusedTextColor = purple,
                unfocusedTextColor = purple,
                cursorColor = purple,
                focusedContainerColor = Color.White.copy(alpha = 0.5f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.5f),

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = regularTextStyle.copy(fontSize = 10.sp),
            modifier = Modifier
                .height(44.dp)
                .width(44.dp)
                .padding(horizontal = 4.dp)
        )

        IconButton(
            onClick = {
                setProductQuantity(item.product.name, quantity.intValue - 1)
                quantity.intValue = item.count
                quantityText.value = item.count.toString()
            },
            modifier = Modifier
                .height(24.dp)
                .width(24.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Remove",
                tint = Color.White
            )
        }
    }
}
