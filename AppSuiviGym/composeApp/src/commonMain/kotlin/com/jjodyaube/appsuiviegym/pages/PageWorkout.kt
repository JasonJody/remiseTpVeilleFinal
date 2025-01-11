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
import com.jjodyaube.appsuiviegym.composants.BoutonAjoutModifier
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

    val isUpdating = remember { mutableStateOf(false) }
    var hasSousWorkout by remember { mutableStateOf(workout.getSousWorkout().isNotEmpty()) }

    fun onDelete() {
        hasSousWorkout = workout.getSousWorkout().isNotEmpty()
    }

    Page(
        appBar = AppBar(navController)
            .titre(workout.getTitre())
            .backButton(true)
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    ListeSousWorkouts(navController, entrainements, workout, isUpdating.value, { onDelete() })
                }
            }

            BoutonAjoutModifier(
                navController,
                hasSousWorkout,
                isUpdating,
                "creer/sous_workout/-1/-1"
            )
        }
    }
}