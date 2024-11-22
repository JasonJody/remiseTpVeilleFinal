package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.FloatingBtnAdd
import com.jjodyaube.appsuiviegym.composants.ListingSousWorkout.ListeSousWorkouts

@Composable
fun PageWorkout(
    navController: NavController,
    entrainements: Structure,
    indexWorkout: Int
) {

    val workout = entrainements.getWorkoutsAt(indexWorkout)

    Page(
        appBar = AppBar(navController)
            .titre(workout.getTitre())
            .backButton(true)
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
                ListeSousWorkouts(navController, entrainements, workout)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                FloatingBtnAdd(navController, "formWorkout")
            }
        }
    }
}