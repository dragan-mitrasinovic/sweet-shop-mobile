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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slatkizalogaji.ui.theme.buttonBorderColor
import com.example.slatkizalogaji.ui.theme.errorTextStyle
import com.example.slatkizalogaji.ui.theme.gradient
import com.example.slatkizalogaji.ui.theme.purple
import com.example.slatkizalogaji.ui.theme.regularTextStyle

@Composable
fun LoginScreen(
    attemptLogin: (String, String) -> Boolean,
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf("") }

    val passwordFocusRequester = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.Transparent,
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(gradient)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Prijava",
                    style = regularTextStyle.copy(fontSize = 24.sp, color = purple)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Korisničko ime",
                    style = regularTextStyle.copy(fontSize = 16.sp, color = purple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(4.dp))

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
                    keyboardActions = KeyboardActions(
                        onNext = {
                            passwordFocusRequester.requestFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "Lozinka",
                    style = regularTextStyle.copy(fontSize = 16.sp, color = purple),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(4.dp))

                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    visualTransformation = PasswordVisualTransformation('*'),
                    colors = TextFieldDefaults.colors().copy(
                        focusedTextColor = purple,
                        unfocusedTextColor = purple,
                        cursorColor = purple,
                        focusedContainerColor = Color.White.copy(alpha = 0.253f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.253f),

                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.focusRequester(passwordFocusRequester),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (!attemptLogin(username.value, password.value)) {
                                username.value = ""
                                password.value = ""
                                errorMessage.value = "Pogrešni kredecijali!"
                            } else {
                                errorMessage.value = ""
                            }

                            localFocusManager.clearFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        if (!attemptLogin(username.value, password.value)) {
                            username.value = ""
                            password.value = ""
                            errorMessage.value = "Pogrešni kredecijali!"
                        } else {
                            errorMessage.value = ""
                        }

                        localFocusManager.clearFocus()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color.White.copy(alpha = 0.253f),
                    ),
                    border = BorderStroke(0.3.dp, buttonBorderColor)
                ) {
                    Text(
                        text = "Prijavi se",
                        style = regularTextStyle.copy(fontSize = 16.sp, color = purple)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (errorMessage.value.isNotEmpty()) {
                    Text(
                        text = errorMessage.value, style = errorTextStyle,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}