package com.mikali.weathermemoir.view.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mikali.weathermemoir.R
import com.mikali.weathermemoir.navigation.NavigationScreens
import com.mikali.weathermemoir.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    navController: NavController
) {
    val state = viewModel.loginObservable.subscribeAsState(
        initial = LoginViewModel.MutableState(
            email = "",
            password = "",
            errorMessage = ""
        )
    ).value
    val isError = remember { mutableStateOf(false) }
    val passwordVisible = remember { mutableStateOf(false) }
    val headerComposition = rememberLottieComposition(
        LottieCompositionSpec.Url("https://assets8.lottiefiles.com/packages/lf20_3Bv8kIT48I.json")
    )

    // we can use this to hide the keyboard, but losing focus or use the explicit keyboard LocalSoftwareKeyboardController
    val focusManager = LocalFocusManager.current
    // allow us to focus on password field when onNext of the username keyboard action is performed
    val passwordFocusRequest = FocusRequester.Default

    val googleLogoImageUrl =
        "https://lh3.googleusercontent.com/COxitqgJr1sJnIDe8-jiKhxDx1FrYbtRHKJ9z_hELisAlapwE9LUPh6fcXIfb5vwpbMl4xl9H9TRFPc5NOO8Sb3VSgIBrfRYvW6cUA"

    if (state.errorMessage.isNotBlank()) {
        ErrorDialog(viewModel, state)
    }

    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Lottie animation, auto cached by the Lottie
        LottieAnimation(
            composition = headerComposition.value,
            iterations = LottieConstants.IterateForever
        )

        Text(
            text = stringResource(id = R.string.header_title),
            modifier = Modifier.fillMaxWidth(),
            style = typography.h6,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.header_sub_title)
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = {
                viewModel.onEmailFieldChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            isError = isError.value,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequest.requestFocus()
                }
            ),
            singleLine = true,
            maxLines = 1,
            shape = CircleShape
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                viewModel.onPasswordFieldChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .focusRequester(passwordFocusRequest),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.VpnKey,
                    contentDescription = "Password Icon"
                )
            },
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
                    focusManager.clearFocus()
                }
            ),
            maxLines = 1,
            shape = CircleShape
        )

        TextButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 8.dp),
            onClick = {}
        ) {
            Text(
                text = "Forgot username and password?"
            )
        }

        // Sign In Button
        Button(
            onClick = {
                if (state.email.isBlank() || state.password.isBlank()) {
                    isError.value = true
                } else {
                    isError.value = false
                    viewModel.loginWithEmailAndPassword(
                        email = state.email,
                        password = state.password,
                        navController = navController
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        ) {
            Text(
                text = "Sign in",
                style = typography.h6
            )
        }

        Text(
            text = "Or",
            color = Color.Gray
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(googleLogoImageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                )
                Text(
                    text = "Continue With Google",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?"
            )
            // Create an account button
            TextButton(onClick = { navController.navigate(route = NavigationScreens.SIGNUP.name) }) {
                Text(
                    text = "Register now"
                )
            }
        }
    }
}
