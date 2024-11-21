package com.jjodyaube.appsuiviegym.utils

import androidx.compose.ui.graphics.Color

fun getLuminance(couleur: Color): Double {
    val r = couleur.red
    val g = couleur.green
    val b = couleur.blue

    return 0.2126 * r + 0.7152 * g + 0.0722 * b
}