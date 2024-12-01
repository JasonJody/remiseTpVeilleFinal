package org.example.premierprojettest

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.getEntrainement
import com.jjodyaube.appsuiviegym.pages.FormCreeExercice
import com.jjodyaube.appsuiviegym.pages.FormCreeSousWorkout
import com.jjodyaube.appsuiviegym.pages.FormCreeWorkout
import com.jjodyaube.appsuiviegym.pages.HomePage
import com.jjodyaube.appsuiviegym.pages.PageExercices
import com.jjodyaube.appsuiviegym.pages.PageHistorique
import com.jjodyaube.appsuiviegym.pages.PageRechercheExercice
import com.jjodyaube.appsuiviegym.pages.PageWorkout
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val entrainements: Structure = getEntrainement()

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
            composable("workout") {
                PageWorkout(navController, entrainements)
            }
            composable("workout/exercices") {
                PageExercices(navController, entrainements)
            }
            composable("historique") {
                PageHistorique(navController, entrainements)
            }
            composable("creer/workout/{idWorkout}") { navBackStackEntry ->
                val workoutId = navBackStackEntry.arguments?.getString("idWorkout")!!.toInt()
                val workout: Workout? = if (workoutId == -1) {
                    null
                } else {
                    entrainements.getWorkoutsAt(workoutId)
                }

                FormCreeWorkout(navController, entrainements, workout)

            }
            composable("creer/sous_workout/{idWorkout}/{idSousWorkout}") { navBackStackEntry ->
                val workoutId = navBackStackEntry.arguments?.getString("idWorkout")!!.toInt()
                val sousWorkoutId = navBackStackEntry.arguments?.getString("idSousWorkout")!!.toInt()
                val sousWorkout: SousWorkout? = if (workoutId == -1 || sousWorkoutId == -1) {
                    null
                } else {
                    val workout = entrainements.getWorkoutsAt(workoutId)
                    workout.getSousWorkoutAt(sousWorkoutId)
                }

                FormCreeSousWorkout(navController, entrainements, sousWorkout)
            }
            composable("creer/exercice") {
                FormCreeExercice(navController, entrainements)
            }
            composable("creer/exercice/recherche") {
                PageRechercheExercice(navController, entrainements)
            }
        }
    }
}