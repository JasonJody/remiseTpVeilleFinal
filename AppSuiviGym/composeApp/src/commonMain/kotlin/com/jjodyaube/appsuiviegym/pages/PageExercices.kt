package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.ExtendedMenuItem
import com.jjodyaube.appsuiviegym.composants.exercice.ListExercices
import com.jjodyaube.appsuiviegym.saveEntrainements
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun PageExercices(navController: NavController, entrainements: Structure) {
    val globalVariable = GlobalVariable.getInstance()

    if(
        globalVariable.getCurrentWorkout() == null
        || globalVariable.getCurrentSousWorkout() == null
        ) {
        navController.popBackStack()
        return
    }

    val sounVibrationIsEnable = remember { mutableStateOf(false) }
    val newWorkoutSet = remember { mutableStateOf(false) }

    val delaiChargementDefault = 0.5
    var delaiChargement by remember { mutableStateOf(delaiChargementDefault) }

    val workout = entrainements.getWorkoutsAt(
        globalVariable.getCurrentWorkout()!!
    )
    val sousWorkout = workout.getSousWorkoutAt(
        globalVariable.getCurrentSousWorkout()!!
    )

    LaunchedEffect(newWorkoutSet.value) {
        if (!newWorkoutSet.value) {
            return@LaunchedEffect
        }

        while(delaiChargement >= 0) {
            delay(0.1.seconds)
            delaiChargement -= 0.1
        }
        delaiChargement = 0.0
    }

    if (newWorkoutSet.value && delaiChargement <= 0.0) {
        saveEntrainements(entrainements)
        delaiChargement = delaiChargementDefault
        newWorkoutSet.value = false
    }

    fun terminerSession() {
        sousWorkout.done()
        newWorkoutSet.value = true
    }

    Page(AppBar(navController)
        .titre(sousWorkout.getTitre())
        .backButton(true)
        .addExtendedMenuItem(ExtendedMenuItem("Ajouter exercice") { navController.navigate("cree/exercice") })
        .addExtendedMenuItem(ExtendedMenuItem("Terminer session") { terminerSession() })
        .addExtendedMenuItem(ExtendedMenuItem((if(sounVibrationIsEnable.value) "Désactiver" else "Activer") + " son/vibration") { sounVibrationIsEnable.value = !sounVibrationIsEnable.value })
        .extendedMenuOffset(-15)
    ) {
        if (!newWorkoutSet.value) {
            ListExercices(navController, entrainements, sousWorkout, sounVibrationIsEnable)
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Chargement des nouveaux entraînements")
            }
        }
    }
}