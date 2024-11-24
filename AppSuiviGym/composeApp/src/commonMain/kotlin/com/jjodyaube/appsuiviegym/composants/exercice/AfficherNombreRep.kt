package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.WorkoutSet

@Composable
fun AfficherNombreRep(nombeRepetition: Int, sizeMultiplicateur: Float = 1f) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            nombeRepetition.toString(),
            color = Color.Black,
            fontSize = (20 * sizeMultiplicateur).sp
        )
        Text("Rep",
            color = Color.Gray,
            fontSize = (11 * sizeMultiplicateur).sp,
            letterSpacing = (-0.1).sp
        )
    }
}