package com.jjodyaube.appsuiviegym.utils

import androidx.compose.ui.graphics.Color

fun getCouleurDependantBg(couleur: Color): Color {
    if (getLuminance(couleur) < 0.5) {
        return Color.White
    }

    return Color.Black
}