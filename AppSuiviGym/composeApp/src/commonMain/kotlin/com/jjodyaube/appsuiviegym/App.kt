package org.example.premierprojettest

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.getHashMapEntrainement
import com.jjodyaube.appsuiviegym.pages.FormCreeWorkout
import com.jjodyaube.appsuiviegym.pages.HomePage
import com.jjodyaube.appsuiviegym.pages.Page
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val entrainements: Structure = getHashMapEntrainement()
    var nbWorkoutCreated = 0

    fun addedAWorkout() {nbWorkoutCreated++}
    fun removedAWorkout() {nbWorkoutCreated--}
    val nbWorkoutToCreateAtStartup = 5

    MaterialTheme {

        val navController = rememberNavController()

        NavHost(
            navController,
            startDestination = "home",
            enterTransition = { EnterTransition.None},
            exitTransition = { ExitTransition.None},
            popEnterTransition = {EnterTransition.None},
            popExitTransition = {ExitTransition.None},
        ) {
            composable("home") {
                HomePage(navController, entrainements)
            }
            composable("formWorkout") {
                FormCreeWorkout(navController, entrainements, nbWorkoutCreated)
            }

            val nbWorkoutTotal = entrainements.getTotalWorkouts().size
            val nbWorkoutToCreate =
                nbWorkoutToCreateAtStartup + nbWorkoutTotal

            for (i in 0 until nbWorkoutToCreate) {
                if (i < nbWorkoutTotal) {
                    val workout = entrainements.getWorkoutsAt(i)
                    if (workout.isDeleted()) continue
                }

                composable("workout/$i") {
                    val workout = entrainements.getWorkoutsAt(i)
                    Page(AppBar(navController).titre(workout.getTitre())) {
                        Text("TEST")
                    }
                }

                if (i < nbWorkoutTotal) {
                    val workout = entrainements.getWorkoutsAt(i)
                    if (workout.isDeleted()) continue

                    val allSousWorkout = workout.getSousWorkout()
                    val nbSousWorkoutToCreate = nbWorkoutToCreateAtStartup + allSousWorkout.size

                    for (j in 0 until nbSousWorkoutToCreate) {
                        val sousWorkout = workout.getSousWorkoutAt(i)

                        if (sousWorkout.isDeleted()) continue

                        composable("workout/$i/$j") {
                            Text(sousWorkout.getTitre())
                        }
                    }

                    continue
                }

                for (j in 0 until nbWorkoutToCreateAtStartup) {
                    composable("workout/$i/$j") {
                        val workout = entrainements.getWorkoutsAt(i)
                        val sousWorkout = workout.getSousWorkoutAt(i)
                        Text(sousWorkout.getTitre())
                    }
                }
            }
        }
    }
}