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
import androidx.compose.ui.Alignment
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
fun ProfileScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    user: MutableState<User?>,
    navigateToChangeProfile: () -> Unit,
    navigateToChangePassword: () -> Unit,
    logout: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        if (user.value == null) {
            return
        }

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
                    text = "Profil",
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
                            value = user.value!!.name,
                            onValueChange = {},
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),
                                disabledContainerColor = Color.White.copy(alpha = 0.253f)
                            ),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                            enabled = false
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    Column {
                        Text(
                            text = "Prezime",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = user.value!!.surname,
                            onValueChange = {},
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),
                                disabledContainerColor = Color.White.copy(alpha = 0.253f)
                            ),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                            enabled = false
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
                            value = user.value!!.address,
                            onValueChange = {},
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),
                                disabledContainerColor = Color.White.copy(alpha = 0.253f)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                            enabled = false
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
                            value = user.value!!.phone,
                            onValueChange = {},
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),
                                disabledContainerColor = Color.White.copy(alpha = 0.253f)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                            enabled = false
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
                            value = user.value!!.username,
                            onValueChange = {},
                            colors = TextFieldDefaults.colors().copy(
                                focusedTextColor = purple,
                                unfocusedTextColor = purple,
                                focusedContainerColor = Color.White.copy(alpha = 0.253f),
                                unfocusedContainerColor = Color.White.copy(alpha = 0.253f),
                                disabledContainerColor = Color.White.copy(alpha = 0.253f)
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = regularTextStyle.copy(fontSize = 14.sp, color = purple),
                            enabled = false
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { navigateToChangePassword() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Green.copy(alpha = 0.15f),
                        ),
                        border = BorderStroke(0.3.dp, buttonBorderColor),
                    ) {
                        Text(
                            text = "Izmeni lozinku",
                            style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                        )
                    }

                    Spacer(modifier = Modifier.width(32.dp))

                    Button(
                        onClick = { navigateToChangeProfile() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Green.copy(alpha = 0.15f),
                        ),
                        border = BorderStroke(0.3.dp, buttonBorderColor)
                    ) {
                        Text(
                            text = "Izmeni podatke",
                            style = regularTextStyle.copy(fontSize = 10.sp, color = purple)
                        )
                    }
                }

                Button(
                    onClick = { logout() },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.Red.copy(alpha = 0.15f),
                    ),
                    border = BorderStroke(0.3.dp, buttonBorderColor),
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Izlogujte se",
                        style = regularTextStyle.copy(fontSize = 10.sp, color = purple),
                    )
                }
            }
        }
    }
}