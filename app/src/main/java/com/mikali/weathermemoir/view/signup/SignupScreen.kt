package com.mikali.weathermemoir.view.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mikali.weathermemoir.navigation.NavigationScreens

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SignupScreen(
    navController: NavController
) {
    val firstName = rememberSaveable { mutableStateOf("") }
    val lastName = rememberSaveable { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val email = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    value = firstName.value,
                    onValueChange = {
                        firstName.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError.value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "First Name (Required)") },
                    placeholder = { Text(text = "First Name") },
                    singleLine = true,
                    maxLines = 1,
                    shape = CircleShape
                )

                OutlinedTextField(
                    value = lastName.value,
                    onValueChange = {
                        lastName.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError.value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text(text = "Last Name (Optional)") },
                    placeholder = { Text(text = "Last Name") },
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
                    label = { Text(text = "Email (Required)") },
                    placeholder = { Text(text = "xxx@xxx.xxx") },
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
                    placeholder = { Text(text = "Password - at least 6 characters") },
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
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    maxLines = 1,
                    shape = CircleShape
                )

                // Create Account Button
                Button(
                    onClick = {
                        if (firstName.value.isBlank() || email.value.isBlank() || password.value.isBlank()) {
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
