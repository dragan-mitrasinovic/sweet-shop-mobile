package com.example.slatkizalogaji.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.data.Notification
import com.example.slatkizalogaji.data.Order
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.fancyTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun NotificationsScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    notifications: MutableState<List<Notification>>,
    setNotificationSeen: (Int) -> Unit,
    goToOrder: (Order) -> Unit
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
                if (notifications.value.isEmpty()) {
                    Text(
                        text = "Nema obaveštenja",
                        style = regularTextStyle.copy(fontSize = 24.sp, color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )
                } else {
                    Text(
                        text = "Obaveštenja",
                        style = regularTextStyle.copy(fontSize = 36.sp, color = Color.White),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )

                    HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
                }

                Notifications(notifications, setNotificationSeen, goToOrder)
            }
        }
    }
}

@Composable
fun Notifications(
    notifications: MutableState<List<Notification>>,
    setNotificationSeen: (Int) -> Unit,
    goToOrder: (Order) -> Unit
) {
    for (notification in notifications.value) {
        Notification(notification, notifications, setNotificationSeen, goToOrder)

        HorizontalDivider(color = Color.White.copy(alpha = 0.5f), thickness = 1.dp)
    }
}

@Composable
fun Notification(
    notification: Notification,
    notifications: MutableState<List<Notification>>,
    setNotificationSeen: (Int) -> Unit,
    goToOrder: (Order) -> Unit
) {
    val seen = remember { mutableStateOf(notification.seen) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(110.dp)
    ) {
        Text(
            text = notification.text,
            style = fancyTextStyle.copy(fontSize = 30.sp, color = Color.White),
            modifier = Modifier
                .padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { goToOrder(notification.order) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Green.copy(alpha = 0.15f),
                ),
                border = BorderStroke(0.3.dp, buttonBorderColor),
            ) {
                Text(
                    text = "Porudžbina",
                    style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val index = notifications.value.indexOf(notification)
                    setNotificationSeen(index)
                    seen.value = true
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    disabledContainerColor = Color.Black.copy(alpha = 0.2f),
                    containerColor = Color.White.copy(alpha = 0.15f),
                ),
                border = BorderStroke(0.3.dp, buttonBorderColor),
                enabled = !seen.value
            ) {
                Text(
                    text = if (seen.value) "Pročitano" else "Novo",
                    style = regularTextStyle.copy(fontSize = 10.sp, color = purple),
                )
            }
        }
    }
}
