package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jjodyaube.appsuiviegym.Exercice
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.HistoriqueCard

@Composable
fun PageHistorique(navController: NavHostController, entrainements: Structure) {

    val globalVariable = GlobalVariable.getInstance()

    var exercice by remember { mutableStateOf<Exercice?>(null) }

    LaunchedEffect(Unit) {
        val workout = entrainements.getWorkoutsAt(globalVariable.getCurrentWorkout()!!)
        val sousWorkout = workout.getSousWorkoutAt(globalVariable.getCurrentSousWorkout()!!)
        exercice = sousWorkout.getExerciceAt(globalVariable.getCurrentExercice()!!)
    }

    Page(
        AppBar(navController)
            .titre(if (exercice == null) "Chargement" else exercice!!.getNom())
            .backButton(true),
    ) {
        if (exercice == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Chargement des données")
            }
        } else {
            val allSets = exercice!!.getAllSets()

            if (allSets.size <= 1) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Aucun historique disponible")
                }
                return@Page
            }

            Column(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                if (allSets.size > 2) {
                    Text("Dernier", letterSpacing = (-0.1).sp)
                    //Avant dernier
                    HistoriqueCard(allSets[allSets.size - 2])
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Text("Premier", letterSpacing = (-0.1).sp)
                HistoriqueCard(allSets.first())

                Spacer(modifier = Modifier.height(30.dp))

                Text("Liste complète", letterSpacing = (-0.1).sp)
                Spacer(modifier = Modifier.height(5.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(allSets.reversed().subList(1, allSets.size)) { set ->
                        HistoriqueCard(set)
                    }
                }
            }
        }
    }
}