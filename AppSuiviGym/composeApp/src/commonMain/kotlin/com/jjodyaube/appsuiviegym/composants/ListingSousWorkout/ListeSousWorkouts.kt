package com.jjodyaube.appsuiviegym.composants.ListingSousWorkout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListeSousWorkouts(
    navController: NavController,
    entrainement: Structure,
    workout: Workout,
    isUpdating: Boolean
) {
    val sousWorkoutGotDeleted = remember { mutableStateOf(false) }

    if (sousWorkoutGotDeleted.value) {
        saveEntrainements(entrainement)
        sousWorkoutGotDeleted.value = false
    }

    if (workout.getSousWorkout().isEmpty()) {
        Text("Aucun jour d’entraînement")
        return
    }


    val listeWorkout = remember { mutableStateOf(workout.getSousWorkout()) }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listeWorkout.value) { sousWorkout ->
            BoutonListeSousWorkout(
                navController,
                entrainement,
                sousWorkout,
                workout,
                sousWorkoutGotDeleted,
                isUpdating,
                listeWorkout
            )
        }
    }
}