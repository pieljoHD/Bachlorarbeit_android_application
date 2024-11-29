package com.example.todolisttestapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolisttestapplication.beige_hell
import com.example.todolisttestapplication.error_red
import com.example.todolisttestapplication.tuerkis_hell

@Composable
fun LoginScreen(
    navController: NavController
) {
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    val isError = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.testTag("UserNameInput"),
            singleLine = true,
            isError = isError.value,
            textStyle = TextStyle(fontSize = 18.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = beige_hell,
                unfocusedContainerColor = beige_hell,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                errorContainerColor = beige_hell
            ),
            value = username.value,
            placeholder = { Text(text = "Benutzername") },
            onValueChange = {
                isError.value = false
                username.value = it
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.testTag("PasswortInput"),
            singleLine = true,
            isError = isError.value,
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = beige_hell,
                unfocusedContainerColor = beige_hell,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                errorContainerColor = beige_hell
            ),
            visualTransformation = PasswordVisualTransformation(),
            value = password.value,
            placeholder = { Text(text = "Passwort") },
            onValueChange = {
                isError.value = false
                password.value = it
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (isError.value) {
            Text(
                modifier = Modifier.testTag("ErrorText"),
                text = "Benutzername oder Passwort ist falsch",
                color = error_red,
                fontSize = 18.sp
            )
        }

        Button(
            modifier = Modifier.testTag("LoginButton"),
            colors = ButtonColors(
                containerColor = tuerkis_hell,
                contentColor = Color.White,
                disabledContentColor = tuerkis_hell,
                disabledContainerColor = tuerkis_hell
            ),
            onClick = {
                //NEVER DO THIS IN A REAL PROJECT!!!!
                if (username.value == "test123" && password.value == "1234") {
                    navController.navigate(Screens.todoScreen.route)
                } else {
                    isError.value = true
                }

            }
        ) {
            Text(text = "login", fontSize = 18.sp)
        }
    }
}