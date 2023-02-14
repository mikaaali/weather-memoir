package com.mikali.weathermemoir.view.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikali.weathermemoir.viewmodel.LoginViewModel

@Composable
fun ErrorDialog(
    viewModel: LoginViewModel,
    state: LoginViewModel.MutableState
) {
    AlertDialog(
        onDismissRequest = { viewModel.onErrorConfirmationClick() },
        confirmButton = {
            TextButton(
                onClick = { viewModel.onErrorConfirmationClick() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Ok")
            }
        },
        title = { Text(text = "Login Error") },
        text = { Text(text = state.errorMessage, style = MaterialTheme.typography.subtitle1) }
    )
}
