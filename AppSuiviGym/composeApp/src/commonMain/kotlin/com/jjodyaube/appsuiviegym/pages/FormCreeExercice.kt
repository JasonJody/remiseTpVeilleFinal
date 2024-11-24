package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.Exercice
import com.jjodyaube.appsuiviegym.Popup
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.InputsAvecTitre
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun FormCreeExercice(
    navController: NavHostController,
    entrainements: Structure,
) {
    val maxSetDispo = 25

    val globalVariable = GlobalVariable.getInstance()

    if(globalVariable.getCurrentWorkout() == null) {
        navController.popBackStack()
        return
    }

    var inputNom by remember { mutableStateOf("") }
    var inputNomHasError by remember { mutableStateOf(false) }
    var inputNbSerie by remember { mutableStateOf("") }
    var inputNbSerieHasError by remember { mutableStateOf(false) }
    val showPopup = remember { mutableStateOf(false) }

    val exerciceRechercheTitreChoisi = globalVariable.getcreeExerciceRechercheTitreChoisi();

    if (exerciceRechercheTitreChoisi != null) {
        inputNom = exerciceRechercheTitreChoisi
        globalVariable.setcreeExerciceRechercheTitreChoisi(null)
    }

    var hasToSaveData by remember { mutableStateOf(false) }

    if (hasToSaveData) {
        saveEntrainements(entrainements)
        navController.popBackStack()
    }

    val popup = Popup(
        showPopup = showPopup,
        popup = {
            CustomAlertDialog(
                "Toutes les information seront perdu. Êtes-vous sur de vouloir quitter ?",
                "Quitter",
                showPopup,
                { navController.popBackStack() })
        }
    )

    fun isValidNumber(toCheck: String): Boolean {
        val regex = "^[0-9]+$".toRegex()
        return toCheck.matches(regex)
    }

    fun verifierLesChamps() {
        if (inputNom.isEmpty()) {
            inputNomHasError = true
        }
        if (!isValidNumber(inputNbSerie)) {
            inputNbSerieHasError = true
        } else if (inputNbSerie.toInt() > maxSetDispo) {
            inputNbSerieHasError = true
        }
    }

    fun envoyerValeurs() {
        verifierLesChamps()
        if (inputNomHasError || inputNbSerieHasError) {
            return
        }
        entrainements.addExercice(inputNom)
        val workout = entrainements.getWorkoutsAt(globalVariable.getCurrentWorkout()!!)
        val sousWorkout = workout.getSousWorkoutAt(globalVariable.getCurrentSousWorkout()!!)
        sousWorkout.addExercice(Exercice(inputNom, inputNbSerie.toInt()))
        hasToSaveData = true
    }

    Page(appBar = AppBar(navController)
        .titre("Nouveau workout")
        .backButton(true)
        .popup(popup)
        .showPopupCondition({ inputNom.isNotEmpty() || inputNbSerie.isNotEmpty() })
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                InputsAvecTitre(
                    titre = "Nom exercice",
                    value = inputNom,
                    onChange = {
                        inputNom = it
                        inputNomHasError = false
                    },
                    placeholder = "Entrez le nom",
                    isError = inputNomHasError
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputsAvecTitre(
                    titre = "Nombre de série",
                    value = inputNbSerie,
                    onChange = {
                        inputNbSerie = it
                        inputNbSerieHasError = false
                    },
                    placeholder = "Entrez le nombre de série",
                    isError = inputNbSerieHasError
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = {
                        navController.navigate("cree/exercice/recherche")
                    },
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    ),
                    contentPadding = PaddingValues(all = 15.dp)
                ) {
                    Text("Rechercher un exercice existant")
                }
            }
            TextButton(
                onClick = { envoyerValeurs() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(all = 15.dp)
            ) {
                Text("Envoyer")
            }
        }
    }
}