package com.mikali.weathermemoir.view.questionnaire

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mikali.weathermemoir.view.theme.Green
import com.mikali.weathermemoir.view.theme.SuperLightGreen
import com.mikali.weathermemoir.viewmodel.QuestionnaireViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun QuestionnaireScreen(
    viewModel: QuestionnaireViewModel
) {
    val state = viewModel.questionnaireObservable.subscribeAsState(
        initial = QuestionnaireViewModel.MutableState(
            firstName = "",
            question = "",
            thought = "",
            readOnly = false
        )
    ).value
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp)
        ) {
            Text(
                text = state.firstName + state.question,
                modifier = Modifier
                    .weight(11f),
                color = Green
            )
            Icon(
                imageVector = Icons.Default.Autorenew,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .weight(1f)
                    .clickable(
                        indication = rememberRipple(bounded = false),
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            viewModel.onToggleClick()
                        }
                    ),
                contentDescription = "Change Question Icon",
                tint = Green
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.End) {
            FloatingActionButton(
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = SuperLightGreen,
                onClick = {
                    keyboardController?.hide()
                    viewModel.onSaveClick()
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = "Save Icon"
                    )
                    Text(text = "Save")
                }
            }
            FloatingActionButton(
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = SuperLightGreen,
                onClick = {
                    viewModel.onEditClick()
                    keyboardController?.show()
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon"
                    )
                    Text(text = "Edit")
                }
            }
            FloatingActionButton(
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(4.dp),
                backgroundColor = SuperLightGreen,
                onClick = {
                    viewModel.onClearClick()
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon"
                    )
                    Text(text = "Clear")
                }
            }
        }

        OutlinedTextField(
            value = state.thought,
            onValueChange = {
                viewModel.onTextFieldChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .fillMaxHeight(0.9f),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Default
            ),
            readOnly = state.readOnly,
            shape = RoundedCornerShape(4.dp)
        )
    }
}
