package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun InputsAvecTitre(
    titre: String,
    value: String,
    onChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean,



    ) {
    val focusManager: FocusManager = LocalFocusManager.current

    Box (modifier = Modifier.fillMaxWidth()) {
        Text(titre)
    }
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        placeholder = { Text(placeholder,  color = Color.LightGray) },
        label = null,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        isError = isError,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = Color.Black,
            focusedIndicatorColor = Color.LightGray,
            cursorColor = if (value.isEmpty()) Color.LightGray else Color.DarkGray,
            focusedLabelColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.LightGray
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
        )
    )
}