package org.example.premierprojettest

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.getHashMapEntrainement
import com.jjodyaube.appsuiviegym.pages.FormCreeWorkout
import com.jjodyaube.appsuiviegym.pages.HomePage
import com.jjodyaube.appsuiviegym.pages.PageWorkout
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val entrainements: Structure = getHashMapEntrainement()
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
                FormCreeWorkout(navController, entrainements)
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
                    PageWorkout(navController, entrainements, i)
                }

                if (i < nbWorkoutTotal) {
                    val workout = entrainements.getWorkoutsAt(i)
                    if (workout.isDeleted()) continue

                    val allSousWorkout = workout.getSousWorkout()
                    val nbSousWorkoutToCreate = nbWorkoutToCreateAtStartup + allSousWorkout.size

                    for (j in 0 until nbSousWorkoutToCreate) {
                        val nbSousWorkoutTotal = workout.getSousWorkout().size
                        if (i < nbSousWorkoutTotal) {
                            val sousWorkout = workout.getSousWorkoutAt(i)
                            if (sousWorkout.isDeleted()) continue
                        }

                        composable("workout/$i/$j") {
                            val sousWorkout = workout.getSousWorkoutAt(i)
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