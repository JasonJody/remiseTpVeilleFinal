package com.jjodyaube.appsuiviegym.composants.ListingSousWorkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.jjodyaube.appsuiviegym.composants.exercice.ExerciceCard
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListeSousWorkouts(
    navController: NavController,
    entrainement: Structure,
    workout: Workout,
    isUpdatingIndexPositions: Boolean
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
                sousWorkout,
                workout,
                sousWorkoutGotDeleted,
                isUpdatingIndexPositions,
                listeWorkout
            )
        }
    }
}