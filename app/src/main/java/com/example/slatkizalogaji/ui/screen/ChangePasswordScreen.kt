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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slatkizalogaji.ui.SweetShopAppBar
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.errorTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun ChangePasswordScreen(
    navController: NavController,
    appBarOpened: MutableState<Boolean>,
    navigateToProfile: () -> Unit,
    updatePassword: (String, String) -> Boolean
) {
    val oldPassword = remember { mutableStateOf("") }
    val newPassword = remember { mutableStateOf("") }
    val confirmNewPassword = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

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
                    text = "Izmena Lozinke",
                    style = regularTextStyle.copy(fontSize = 36.sp, color = purple),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Stara lozinka",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = oldPassword.value,
                            onValueChange = { oldPassword.value = it },
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
                            visualTransformation = PasswordVisualTransformation('*')
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Nova lozinka",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = newPassword.value,
                            onValueChange = { newPassword.value = it },
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
                            visualTransformation = PasswordVisualTransformation('*')
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Potvrda nove lozinke",
                            style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                        )

                        TextField(
                            value = confirmNewPassword.value,
                            onValueChange = { confirmNewPassword.value = it },
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
                            visualTransformation = PasswordVisualTransformation('*')
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 20.dp)
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
                            if (newPassword.value == confirmNewPassword.value) {
                                val success = updatePassword(oldPassword.value, newPassword.value)
                                if (success) {
                                    navigateToProfile()
                                } else {
                                    errorMessage.value = "Pogrešna stara lozinka!"
                                    oldPassword.value = ""
                                }
                            } else {
                                errorMessage.value = "Lozinke se ne poklapaju!"
                                newPassword.value = ""
                                confirmNewPassword.value = ""
                            }
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

                if (errorMessage.value.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                    ) {
                        Text(
                            text = errorMessage.value, style = errorTextStyle,
                        )
                    }
                }
            }
        }
    }
}