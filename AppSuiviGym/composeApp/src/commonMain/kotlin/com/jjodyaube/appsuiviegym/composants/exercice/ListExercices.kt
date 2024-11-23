package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.SousWorkout

@Composable
fun ListExercices(navController: NavController, sousWorkout: SousWorkout) {
    if (sousWorkout.getExercices().isEmpty()) {
        Text("Aucun exercice")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        for (exercice in sousWorkout.getExercices()) {
            ExerciceCard(
                exercice
            )
        }
    }
}