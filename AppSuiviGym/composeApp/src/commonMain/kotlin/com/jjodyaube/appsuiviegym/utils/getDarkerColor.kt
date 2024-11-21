package com.jjodyaube.appsuiviegym.utils

import androidx.compose.ui.graphics.Color

fun getDarkerColor(color: Color, factor: Float = 0.1f): Color {
    val red = color.red - factor
    val green = color.green - factor
    val blue = color.blue - factor

    return Color(
        red = red.coerceIn(0f, 1f),
        green = green.coerceIn(0f, 1f),
        blue = blue.coerceIn(0f, 1f),
        alpha = color.alpha
    )
}