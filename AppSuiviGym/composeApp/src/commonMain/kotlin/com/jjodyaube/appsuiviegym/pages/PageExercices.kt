package com.jjodyaube.appsuiviegym.pages

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.CurrentWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.ExtendedMenuItem
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

    val sounVibrationIsEnable = remember { mutableStateOf(false) }

    val workout = entrainements.getWorkoutsAt(
        currentWorkout.getCurrentWorkout()!!
    )
    val sousWorkout = workout.getSousWorkoutAt(
        currentWorkout.getCurrentSousWorkout()!!
    )

    fun terminerSession() {

    }

    Page(AppBar(navController)
        .titre(sousWorkout.getTitre())
        .backButton(true)
        .addExtendedMenuItem(ExtendedMenuItem("Ajouter exercice") { navController.navigate("cree/exercice") })
        .addExtendedMenuItem(ExtendedMenuItem("Terminer session") { terminerSession() })
        .addExtendedMenuItem(ExtendedMenuItem((if(sounVibrationIsEnable.value) "Désactiver" else "Activer") + " son/vibration") { sounVibrationIsEnable.value = !sounVibrationIsEnable.value })
        .extendedMenuOffset(-15)
    ) {
        ListExercices(entrainements, sousWorkout)
    }
}