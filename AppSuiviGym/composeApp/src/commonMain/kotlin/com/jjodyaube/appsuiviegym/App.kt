package org.example.premierprojettest

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.getEntrainement
import com.jjodyaube.appsuiviegym.pages.FormCreeExercice
import com.jjodyaube.appsuiviegym.pages.FormCreeSousWorkout
import com.jjodyaube.appsuiviegym.pages.FormCreeWorkout
import com.jjodyaube.appsuiviegym.pages.HomePage
import com.jjodyaube.appsuiviegym.pages.PageExercices
import com.jjodyaube.appsuiviegym.pages.PageHistorique
import com.jjodyaube.appsuiviegym.pages.PageWorkout
import com.jjodyaube.appsuiviegym.pages.PageRechercheExercice
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val entrainements: Structure = getEntrainement()
//    var titreRechercheeChoisi: String? = null

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
                PageHistorique(navController)
            }

            composable("cree/workout") {
                FormCreeWorkout(navController, entrainements)
            }
            composable("cree/sous_workout") {
                FormCreeSousWorkout(navController, entrainements)
            }
            composable("cree/exercice") {
                FormCreeExercice(navController, entrainements)
            }
            composable("cree/exercice/recherche") {
                PageRechercheExercice(navController, entrainements)
            }
        }
    }
}