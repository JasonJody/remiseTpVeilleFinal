package com.jjodyaube.appsuiviegym.pages

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.CurrentWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.exercice.ListExercices

@Composable
fun PageExercices(navController: NavController, entrainements: Structure) {
    val currentWorkout = CurrentWorkout.getInstance()

    if(
        currentWorkout.getCurrentWorkout() == null
        || currentWorkout.getCurrentSousWorkout() == null
        ) {
        navController.popBackStack()
        return
    }

    val workout = entrainements.getWorkoutsAt(
        currentWorkout.getCurrentWorkout()!!
    )
    val sousWorkout = workout.getSousWorkoutAt(
        currentWorkout.getCurrentSousWorkout()!!
    )

    Page(AppBar(navController)
        .titre(sousWorkout.getTitre())
        .backButton(true)
        .actionButtonIcon(Icons.Filled.Add)
        .actionButtonDescription( "Ajout Exercice")
        .actionButtonAction {
            navController.navigate("cree/exercice")
        }
    ) {
        ListExercices(entrainements, sousWorkout)
    }
}