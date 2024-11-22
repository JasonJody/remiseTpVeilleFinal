package com.jjodyaube.appsuiviegym.composants.ListingSousWorkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.ListingWorkouts.BoutonListeWorkout
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListeSousWorkouts(
    navController: NavController,
    entrainement: Structure,
    workout: Workout,
) {
    var sousWorkoutGotDeleted = remember { mutableStateOf(false) }

    if (sousWorkoutGotDeleted.value) {
        saveEntrainements(entrainement)
        sousWorkoutGotDeleted.value = false
    }

    if (workout.getSousWorkout().isEmpty()) {
        Text("Aucun jour d’entraînement")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        for (sousWorkout in workout.getTotalSousWorkout()) {
            BoutonListeSousWorkout(
                navController,
                entrainement,
                sousWorkout,
                workout,
                sousWorkoutGotDeleted
            )
        }
    }
}