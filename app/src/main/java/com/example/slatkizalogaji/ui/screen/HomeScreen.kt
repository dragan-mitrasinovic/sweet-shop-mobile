package com.example.slatkizalogaji.ui.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.R
import com.example.slatkizalogaji.data.Product
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.fancyTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.regularTextStyle
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    product: MutableState<Product>,
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    onClickProduct: () -> Unit,
    showLeftButton: MutableState<Boolean>,
    showRightButton: MutableState<Boolean>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SweetShopAppBar(appBarOpened, navController)

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftButton(onClickPrev, showLeftButton)

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Column(
                    modifier = Modifier.background(gradient),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = product.value.name,
                        style = fancyTextStyle.copy(fontSize = 40.sp, color = Color.White),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(id = product.value.image),
                        contentDescription = "Cake",
                        modifier = Modifier
                            .width(210.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable { onClickProduct() }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = product.value.desc,
                        style = regularTextStyle.copy(fontSize = 16.sp, color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 36.dp),
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            RightButton(onClickNext, showRightButton)
        }
    }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(3000)
            onClickNext()
        }
    }
}

@Composable
fun LeftButton(onClickPrev: () -> Unit, showLeftButton: MutableState<Boolean>) {
    if (showLeftButton.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 12.dp)
                .clickable { onClickPrev() }
        ) {
            Canvas(modifier = Modifier.size(48.dp)) {
                drawCircle(brush = gradient)
            }

            Image(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "Left arrow",
            )
        }
    } else {
        Box(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(48.dp)
        ) {}
    }
}

@Composable
fun RightButton(onClickNext: () -> Unit, showRightButton: MutableState<Boolean>) {
    if (showRightButton.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(end = 12.dp)
                .clickable { onClickNext() }
        ) {
            Canvas(modifier = Modifier.size(48.dp)) {
                drawCircle(brush = gradient)
            }

            Image(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "Left arrow",
            )
        }
    } else {
        Box(
            modifier = Modifier
                .padding(end = 12.dp)
                .size(48.dp)
        ) {}
    }
}