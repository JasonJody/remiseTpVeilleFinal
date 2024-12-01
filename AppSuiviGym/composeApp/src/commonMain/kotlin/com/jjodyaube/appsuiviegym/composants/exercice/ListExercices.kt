package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListExercices(
    navController: NavController,
    entrainement: Structure,
    sousWorkout: SousWorkout,
    sounVibrationIsEnable: MutableState<Boolean>
) {
    if (sousWorkout.getExercices().isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Aucun exercice")
        }
        return
    }
    val exerciceGotDeleted = remember { mutableStateOf(false) }

    if (exerciceGotDeleted.value) {
        saveEntrainements(entrainement)
        exerciceGotDeleted.value = false
    }

    val listeExecices = remember { mutableStateOf(sousWorkout.getExercices()) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        for (exercice in listeExecices.value) {
            ExerciceCard(
                navController,
                entrainement,
                sousWorkout,
                exercice,
                sounVibrationIsEnable,
            )
        }

    }
}