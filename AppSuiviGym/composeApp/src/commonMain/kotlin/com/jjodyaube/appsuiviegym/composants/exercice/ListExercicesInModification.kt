package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListExercicesInModification(
    navController: NavController,
    entrainement: Structure,
    sousWorkout: SousWorkout,
    onDelete: () -> Unit
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
    val exerciceGotModified = remember { mutableStateOf(false) }
    val listeExecices = remember { mutableStateOf(sousWorkout.getExercices()) }

    if (exerciceGotModified.value) {
        saveEntrainements(entrainement)
        exerciceGotModified.value = false
    }


    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        for (exercice in listeExecices.value) {
            ExerciceCardModify(
                navController,
                sousWorkout,
                exercice,
                exerciceGotModified,
                listeExecices,
                onDelete
            )
        }
        Spacer(Modifier.height(120.dp))
    }
}