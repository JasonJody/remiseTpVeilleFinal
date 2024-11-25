package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.TypeEquipement
import com.jjodyaube.appsuiviegym.WorkoutSet

@Composable
fun AfficherPoids(set: WorkoutSet, sizeMultiplicateur: Float = 1f, isCentered: Boolean = false, maxSize: Int? = 80) {
    val poids = set.getPoids()
    val typeEquipement = set.getTypeEquipement()
    val uniteDeMesure = set.getUniteDeMesure()
    val (nombrePlate, nombreKgSup) = set.getNombrePlaqueEtKgSupplementaire()

    val witdhModifier = if (maxSize == null)
        Modifier.widthIn(80.dp)
    else
        Modifier.widthIn(80.dp, maxSize.dp)

    Box(
        modifier = witdhModifier,
        contentAlignment = if (isCentered)  Alignment.Center else Alignment.CenterEnd
    ) {
        if (poids == 0f) {
            Text("Sans",
                color = Color.Black,
                fontSize = (16 * sizeMultiplicateur).sp
            )
        } else if (typeEquipement == TypeEquipement.PLATES) {
            AfficherPoidsPlate(nombrePlate, nombreKgSup, uniteDeMesure, sizeMultiplicateur, isCentered)
        } else if (typeEquipement == TypeEquipement.DUMBBELLS) {
            AfficherPoidsDumbbell(poids, uniteDeMesure, sizeMultiplicateur, isCentered)
        }
    }
}