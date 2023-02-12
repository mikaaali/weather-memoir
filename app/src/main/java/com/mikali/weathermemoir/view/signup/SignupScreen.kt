package com.mikali.weathermemoir.view.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikali.weathermemoir.navigation.NavigationScreens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignupScreen(
    navController: NavController
) {
    val username = rememberSaveable { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val numberText = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }

    BackdropScaffold(
        appBar = { /*TODO*/ },
        backLayerContent = {},
        frontLayerContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box() {
                    // Back to Login screen
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterStart)
                            .clickable(
                                indication = rememberRipple(bounded = false),
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                navController.navigate(NavigationScreens.LOGIN.name)
                            },
                        contentDescription = "ArrowBack Icon"
                    )
                    Text(
                        text = "Sign up",
                        style = typography.h3,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                OutlinedTextField(
                    value = username.value,
                    onValueChange = {
                        username.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError.value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Username (Required)") },
                    placeholder = { Text(text = "Username") },
                    singleLine = true,
                    maxLines = 1,
                    shape = CircleShape
                )

                OutlinedTextField(
                    value = numberText.value,
                    onValueChange = {
                        numberText.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Phone number (Optional)") },
                    placeholder = { Text(text = "000-000-0000") },
                    singleLine = true,
                    maxLines = 1,
                    shape = CircleShape
                )

                OutlinedTextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Email (Optional)") },
                    placeholder = { Text(text = "000-000-0000") },
                    singleLine = true,
                    maxLines = 1,
                    shape = CircleShape

                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    label = { Text(text = "Password (Required)") },
                    placeholder = { Text(text = "Password") },
                    trailingIcon = {
                        Icon(
                            imageVector = if (passwordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            modifier = Modifier.clickable(
                                // make the ripple not bounded to just the box
                                indication = rememberRipple(bounded = false),
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                passwordVisible.value = !passwordVisible.value
                            },
                            contentDescription = "Visibility Icon"
                        )
                    },
                    isError = isError.value,
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    maxLines = 1,
                    shape = CircleShape
                )

                // Create Account Button
                Button(
                    onClick = {
                        if (username.value.isBlank() || password.value.isBlank()) {
                            isError.value = true
                        } else {
                            isError.value = false
                            navController.navigate(route = NavigationScreens.MAIN.name)
                        }
                    },
                    shape = CircleShape
                ) {
                    Text(text = "CREATE ACCOUNT")
                }
            }
        }
    )
}
