package org.example.premierprojettest.org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import org.example.premierprojettest.getBoutonCreeFichier

@Composable
fun PageCreeFichier() {
    var inputValue by remember { mutableStateOf("") }
    var inputHasError by remember { mutableStateOf(false) }

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
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                getBoutonCreeFichier(
                    inputValue,
                    {hasError: Boolean -> inputHasError = hasError},
                    { inputValue = ""},
                )()
            }
        }
    }
}
