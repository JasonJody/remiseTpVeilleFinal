package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageInputs() {
    var inputValue by remember { mutableStateOf("") }
    var inputHasError by remember { mutableStateOf(false) }
    var check1 by remember { mutableStateOf(false) }
    var check2 by remember { mutableStateOf(false) }
    var check3 by remember { mutableStateOf(true) }

    fun envoyerValeurs() {
        if (inputValue.length <= 0) {
            inputHasError = true
            return
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier
                    .fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = inputValue,
                    onValueChange = {
                        inputValue = it
                        inputHasError = false
                    },
                    label = { Text("Entrer une valeur") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = inputHasError,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Checkbox(
                        checked = check1,
                        onCheckedChange = {check1 = !check1},
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            checkmarkColor = Color.White,
                        )
                    )
                    Checkbox(
                        checked = check2,
                        onCheckedChange = {check2 = !check2},
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            checkmarkColor = Color.White,
                        )
                    )
                    Checkbox(
                        checked = check3,
                        onCheckedChange = {check3 = !check3},
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color.Black,
                            checkmarkColor = Color.White,
                        )
                    )
                }
            }
            Button(
                onClick = { envoyerValeurs() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(all = 15.dp)
            ) {
                Text("Envoyer")
            }
        }
    }
}
