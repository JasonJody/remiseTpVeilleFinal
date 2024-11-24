package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedButton(text: String, action: () -> Unit, height: Int = 40, icon: ImageVector? =  null, iconDescription: String = "") {
    TextButton(
        onClick = action,
        modifier = Modifier.height(height.dp).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            letterSpacing = (-0.1).sp
        )
        if (icon != null) {
            Icon(icon, iconDescription)
        }
    }
}