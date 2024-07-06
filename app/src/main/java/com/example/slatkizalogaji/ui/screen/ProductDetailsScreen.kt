package com.example.slatkizalogaji.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.R
import com.example.slatkizalogaji.data.Product
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.fancyTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    product: MutableState<Product?>,
    addComment: (String) -> Unit,
    addToCart: (Int) -> Unit
) {
    val commentText = remember { mutableStateOf("") }
    val quantity = remember { mutableIntStateOf(1) }
    val quantityText = remember { mutableStateOf("1") }

    val context = LocalContext.current

    if (product.value == null) {
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SweetShopAppBar(appBarOpened, navController)

        Column(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(horizontal = 32.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.Transparent,
                ),
            ) {
                Box(
                    modifier = Modifier
                        .background(gradient)
                        .fillMaxSize()
                ) {
                    Text(
                        text = product.value!!.name,
                        style = fancyTextStyle.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth(0.5f)
                            .padding(top = 58.dp, start = 16.dp, end = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = product.value!!.image),
                            contentDescription = "Product Image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Spacer(
                            modifier = Modifier
                                .height(16.dp)
                                .weight(1f)
                        )

                        Row(
                            modifier = Modifier
                                .padding(bottom = 18.dp)
                                .height(44.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(end = 6.dp)
                            ) {
                                IconButton(
                                    onClick = {
                                        quantityText.value = (quantity.intValue + 1).toString()
                                        quantity.intValue++
                                    },
                                    modifier = Modifier
                                        .height(16.dp)
                                        .width(16.dp),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "Add",
                                        tint = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                IconButton(
                                    onClick = {
                                        if (quantity.intValue != 0) {
                                            quantityText.value = (quantity.intValue - 1).toString()
                                            quantity.intValue--
                                        }
                                    },
                                    modifier = Modifier
                                        .height(16.dp)
                                        .width(16.dp),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.minus),
                                        contentDescription = "Remove",
                                        tint = Color.White
                                    )
                                }
                            }

                            TextField(
                                value = quantityText.value,
                                onValueChange = {
                                    quantityText.value = it
                                    if (it.isNotEmpty()) {
                                        quantity.intValue = it.toInt()
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
                                textStyle = TextStyle(fontSize = 13.sp),
                                modifier = Modifier
                                    .height(44.dp)
                                    .width(44.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(16.dp)
                                    .weight(1f)
                            )

                            IconButton(
                                onClick = {
                                    if (quantity.intValue != 0) {
                                        addToCart(quantity.intValue)
                                        quantity.intValue = 1
                                        quantityText.value = "1"

                                        Toast.makeText(
                                            context,
                                            "Proizvod dodat u korpu",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cartadd),
                                    contentDescription = "Add to Cart",
                                    tint = Color.White
                                )
                            }
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .fillMaxWidth(0.5f)
                            .padding(top = 58.dp, end = 16.dp)
                    ) {

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = product.value!!.desc,
                            style = regularTextStyle.copy(
                                fontSize = 12.sp,
                                color = Color.White,
                                letterSpacing = 2.sp
                            ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = product.value!!.ingredients,
                            style = regularTextStyle.copy(
                                fontSize = 12.sp,
                                color = Color.White,
                                letterSpacing = 2.sp
                            ),
                            textAlign = TextAlign.Center
                        )

                        Spacer(
                            modifier = Modifier
                                .height(32.dp)
                                .weight(1f)
                        )

                        Text(
                            text = "Cena: ${product.value!!.price * quantity.intValue} RSD",
                            style = fancyTextStyle.copy(fontSize = 38.sp, color = Color.White),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                    }
                }

            }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.Transparent,
                ),
            ) {
                Column(
                    modifier = Modifier
                        .background(gradient)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Komentari",
                        style = fancyTextStyle.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 48.sp,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    for (comment in product.value!!.comments) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = comment.username + ": ",
                                style = regularTextStyle.copy(
                                    fontSize = 12.sp,
                                    color = Color.White
                                ),
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = comment.text,
                                style = regularTextStyle.copy(
                                    fontSize = 12.sp,
                                    color = Color.White
                                ),
                                textAlign = TextAlign.Start
                            )
                        }

                        HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
                    }

                    Spacer(
                        modifier = Modifier
                            .height(32.dp)
                            .weight(1f)
                    )

                    TextField(
                        value = commentText.value,
                        onValueChange = { commentText.value = it },
                        colors = TextFieldDefaults.colors().copy(
                            focusedTextColor = purple,
                            unfocusedTextColor = purple,
                            cursorColor = purple,
                            focusedContainerColor = Color.White.copy(alpha = 0.253f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.253f),

                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    Button(
                        onClick = {
                            if (commentText.value.isNotEmpty()) {
                                addComment(commentText.value)
                                commentText.value = ""
                            }
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Green.copy(alpha = 0.15f),
                        ),
                        border = BorderStroke(0.3.dp, buttonBorderColor),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 16.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = "Potvrdi",
                            style = regularTextStyle.copy(fontSize = 12.sp, color = purple)
                        )
                    }
                }
            }
        }
    }
}