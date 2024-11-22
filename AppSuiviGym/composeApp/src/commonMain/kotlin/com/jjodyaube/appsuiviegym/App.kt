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
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.getHashMapEntrainement
import com.jjodyaube.appsuiviegym.pages.FormCreeSousWorkout
import com.jjodyaube.appsuiviegym.pages.FormCreeWorkout
import com.jjodyaube.appsuiviegym.pages.HomePage
import com.jjodyaube.appsuiviegym.pages.Page
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
                HomePage(navController, entrainements, nbWorkoutToCreateAtStartup)
            }
            composable("formWorkout") {
                FormCreeWorkout(navController, entrainements)
            }

            val nbWorkoutTotal = entrainements.getTotalWorkouts().size
            val nbWorkoutToCreate =
                nbWorkoutToCreateAtStartup + nbWorkoutTotal

            for (indexWorkout in 0 until nbWorkoutToCreate) {
                if (indexWorkout < nbWorkoutTotal) {
                    val workout = entrainements.getWorkoutsAt(indexWorkout)
                    if (workout.isDeleted()) continue
                }

                composable("workout/$indexWorkout") {
                    PageWorkout(navController, entrainements, indexWorkout, nbWorkoutToCreateAtStartup)
                }
                composable("workout/$indexWorkout/cree") {
                    FormCreeSousWorkout(navController, entrainements, indexWorkout)
                }

                if (indexWorkout < nbWorkoutTotal) {
                    val workout = entrainements.getWorkoutsAt(indexWorkout)
                    if (workout.isDeleted()) continue

                    val nbSousWorkoutTotal = workout.getTotalSousWorkout().size
                    val nbSousWorkoutToCreate = nbWorkoutToCreateAtStartup + nbSousWorkoutTotal

                    for (indexSousWorkout in 0 until nbSousWorkoutToCreate) {
                        if (indexSousWorkout < nbSousWorkoutTotal) {
                            val sousWorkout = workout.getSousWorkoutAt(indexSousWorkout)
                            if (sousWorkout.isDeleted()) continue
                        }

                        composable("workout/$indexWorkout/$indexSousWorkout") {
                            val sousWorkout = workout.getSousWorkoutAt(indexWorkout)
                            Page(AppBar(navController)
                                .titre(sousWorkout.getTitre())
                                .backButton(true)) {
                                Text("HEHE")
                            }
                        }
                    }

                    continue
                }

                for (indexSousWorkout in 0 until nbWorkoutToCreateAtStartup) {
                    composable("workout/$indexWorkout/${indexSousWorkout}") {
                        val workout = entrainements.getWorkoutsAt(indexWorkout)
                        val sousWorkout = workout.getSousWorkoutAt(indexWorkout)
                        Page(AppBar(navController)
                            .titre(sousWorkout.getTitre())
                            .backButton(true)) {
                            Text("HEHE")
                        }
                    }
                }
            }
        }
    }
}