package com.mikali.weathermemoir.view.questionnaire

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mikali.weathermemoir.view.theme.Cyan
import com.mikali.weathermemoir.view.theme.Green
import com.mikali.weathermemoir.view.theme.LightGreen
import com.mikali.weathermemoir.view.theme.SuperLightGreen
import com.mikali.weathermemoir.view.theme.Teal

@Composable
@Preview
fun QuestionnaireScreen() {
    // val textInput = remember { mutableStateOf("") }
    val textInput = rememberSaveable { mutableStateOf("") }
    val readOnly = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(SuperLightGreen)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "What is your earliest memory?",
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(2f),
                    style = typography.h6
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Autorenew,
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { },
                        contentDescription = "Change Question Icon"
                    )
                    Text(
                        text = "Switch Q",
                        style = typography.caption,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.White,
                border = BorderStroke(
                    width = 4.dp,
                    brush = Brush.linearGradient(listOf(Cyan, Teal, Green, LightGreen))
                )
            ) {
                BasicTextField(
                    value = textInput.value,
                    onValueChange = {
                        textInput.value = it
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    readOnly = readOnly.value,
                    textStyle = typography.body1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    cursorBrush = SolidColor(Green)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = SuperLightGreen,
                onClick = {
                    readOnly.value = true
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
                modifier = Modifier
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = SuperLightGreen,
                onClick = {
                    readOnly.value = false
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
        }
    }
}
