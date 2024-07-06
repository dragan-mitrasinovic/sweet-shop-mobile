package com.example.slatkizalogaji.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.data.User
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun ChangeProfileScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    user: MutableState<User?>,
    navigateToProfile: () -> Unit,
    updateUser: (User) -> Unit
) {
    if (user.value == null) {
        return
    }

    val name = remember { mutableStateOf(user.value!!.name) }
    val surname = remember { mutableStateOf(user.value!!.surname) }
    val address = remember { mutableStateOf(user.value!!.address) }
    val phone = remember { mutableStateOf(user.value!!.phone) }
    val username = remember { mutableStateOf(user.value!!.username) }

    Column(modifier = Modifier.fillMaxSize()) {
        SweetShopAppBar(appBarOpened, navController)

        Spacer(modifier = Modifier.height(80.dp))

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
                    text = "Izmena Profila",
                    style = regularTextStyle.copy(fontSize = 36.sp, color = purple),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth(0.3f)) {
                        Text(
                            text = "Ime",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = name.value,
                            onValueChange = { name.value = it },
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
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    Column {
                        Text(
                            text = "Prezime",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = surname.value,
                            onValueChange = { surname.value = it },
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                cursorColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),

                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            textStyle = regularTextStyle.copy(fontSize = 16.sp, color = purple),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Adresa",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = address.value,
                            onValueChange = { address.value = it },
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                cursorColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),

                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Telefon",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = phone.value,
                            onValueChange = { phone.value = it },
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                cursorColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),

                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Korisničko ime",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )
                        TextField(
                            value = username.value,
                            onValueChange = { username.value = it },
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                cursorColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),

                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { navigateToProfile() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Red.copy(alpha = 0.15f),
                        ),
                        border = BorderStroke(0.3.dp, buttonBorderColor)
                    ) {
                        Text(
                            text = "Odustani",
                            style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                        )
                    }

                    Spacer(modifier = Modifier.width(48.dp))

                    Button(
                        onClick = {
                            updateUser(User().apply {
                                this.name = name.value
                                this.surname = surname.value
                                this.address = address.value
                                this.phone = phone.value
                                this.username = username.value
                            })
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Green.copy(alpha = 0.15f),
                        ),
                        border = BorderStroke(0.3.dp, buttonBorderColor)
                    ) {
                        Text(
                            text = "Potvrdi",
                            style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                        )
                    }
                }
            }
        }
    }
}