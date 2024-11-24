package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.UniteDeMesure
import com.jjodyaube.appsuiviegym.utils.getPluriel

fun getPoidsFormater(nombre: Float): String {
    return if (nombre == nombre.toInt().toFloat()) {
        nombre.toInt().toString()
    } else {
        nombre.toString()
    }
}

@Composable
fun AfficherPoidsPlate(
    nombrePlate: Float,
    nombreKgSupplementaire: Float,
    uniteDeMesure: UniteDeMesure,
    sizeMultiplicateur: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (nombrePlate > 0) {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text("${nombrePlate.toInt()}",
                    color = Color.Black,
                    fontSize = (20 * sizeMultiplicateur).sp
                )
                Text(
                    getPluriel(nombrePlate.toInt(), "plate"),
                    color = Color.Gray,
                    fontSize = (11 * sizeMultiplicateur).sp,
                    letterSpacing = (-0.1).sp
                )
            }
        }
        if (nombreKgSupplementaire > 0) {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    getPoidsFormater(nombreKgSupplementaire),
                    color = Color.Black,
                    fontSize = (20 * sizeMultiplicateur).sp
                )
                Text(
                    uniteDeMesure.toString().lowercase(),
                    color = Color.Gray,
                    fontSize = (11 * sizeMultiplicateur).sp,
                    letterSpacing = (-0.1).sp
                )
            }
        }
    }
}