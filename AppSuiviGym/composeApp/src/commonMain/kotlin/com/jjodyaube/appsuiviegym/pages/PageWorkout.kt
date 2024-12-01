package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
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
import com.jjodyaube.appsuiviegym.composants.FloatingBtnAdd
import com.jjodyaube.appsuiviegym.composants.listingSousWorkout.ListeSousWorkouts

@Composable
fun PageWorkout(
    navController: NavController,
    entrainements: Structure
) {

    val globalVariable = GlobalVariable.getInstance()

    if(globalVariable.getCurrentWorkout() == null) {
        navController.popBackStack()
        return
    }

    val workout = entrainements.getWorkoutsAt(globalVariable.getCurrentWorkout()!!)

    var isUpdating by remember { mutableStateOf(false) }

    Page(
        appBar = AppBar(navController)
            .titre(workout.getTitre())
            .backButton(true)
            .addExtendedMenuItem(ExtendedMenuItem(
                "${if (isUpdating) "Désactiver" else "Activer"} modification"
            ) { isUpdating = !isUpdating })
            .extendedMenuOffset(-15)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                ListeSousWorkouts(navController, entrainements, workout, isUpdating)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                FloatingBtnAdd(navController, "creer/sous_workout/-1/-1")
            }
        }
    }
}