package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    onClick: () -> Unit,
    size: Int = 40,
    icon: ImageVector,
    description: String,
    contentColor: Color = Color.Black,
    backgroundColor: Color = Color.Transparent,
    modifier: Modifier = Modifier,
    iconPadding: Int = 0
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.size(size.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            backgroundColor = backgroundColor
        )
    ) {
        Icon(icon, contentDescription = description, modifier = Modifier.padding(iconPadding.dp))
    }
}