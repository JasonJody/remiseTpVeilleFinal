package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.UniteDeMesure

@Composable
fun AfficherPoidsDumbbell(
    poids: Float,
    uniteDeMesure: UniteDeMesure,
    sizeMultiplicateur: Float,
    isCentered: Boolean
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            getPoidsFormater(poids),
            color = Color.Black,
            fontSize = (20 * sizeMultiplicateur).sp
        )
        Text(uniteDeMesure.toString().lowercase(),
            color = Color.Gray,
            fontSize = (11 * sizeMultiplicateur).sp,
            letterSpacing = (-0.1).sp
        )
    }
}