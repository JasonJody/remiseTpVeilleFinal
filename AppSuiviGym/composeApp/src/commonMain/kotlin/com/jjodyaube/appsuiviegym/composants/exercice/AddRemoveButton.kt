package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun AddRemoveButton(text: String, action: () -> Unit) {
    TextButton(
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(text, fontSize = 15.sp)
    }
}