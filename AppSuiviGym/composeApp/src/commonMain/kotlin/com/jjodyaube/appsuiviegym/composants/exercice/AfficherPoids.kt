package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.TypeEquipement
import com.jjodyaube.appsuiviegym.WorkoutSet

@Composable
fun AfficherPoids(set: WorkoutSet, sizeMultiplicateur: Float = 1f) {
    val poids = set.getPoids()
    val typeEquipement = set.getTypeEquipement()
    val uniteDeMesure = set.getUniteDeMesure()
    val (nombrePlate, nombreKgSup) = set.getNombrePlaqueEtKgSupplementaire()

    if (poids == 0f) {
        Text("Sans",
            color = Color.Black,
            fontSize = (16 * sizeMultiplicateur).sp
        )
    } else if (typeEquipement == TypeEquipement.PLATES) {
        AfficherPoidsPlate(nombrePlate, nombreKgSup, uniteDeMesure, sizeMultiplicateur)
    } else if (typeEquipement == TypeEquipement.DUMBBELLS) {
        AfficherPoidsDumbbell(poids, uniteDeMesure, sizeMultiplicateur)
    }
}