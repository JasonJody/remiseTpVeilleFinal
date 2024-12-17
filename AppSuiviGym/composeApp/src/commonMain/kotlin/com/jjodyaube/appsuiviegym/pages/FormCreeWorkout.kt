package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.jjodyaube.appsuiviegym.Jours
import com.jjodyaube.appsuiviegym.Popup
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.CheckJourSemaine
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.InputsAvecTitre
import com.jjodyaube.appsuiviegym.composants.RoueDeCouleur
import com.jjodyaube.appsuiviegym.saveEntrainements
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg

@Composable
fun FormCreeWorkout(
    navController: NavHostController,
    entrainements: Structure,
    workout: Workout?,
) {
    val showPopup = remember { mutableStateOf(false) }

    val listeJournees = remember { mutableStateListOf<Jours>() }
    var inputTitre by remember { mutableStateOf("") }
    var inputTitreHasError by remember { mutableStateOf(false) }
    var couleurActive by remember { mutableStateOf(Color.White) }

    if (workout != null) {
        listeJournees.addAll(workout.getJournees())
        inputTitre = workout.getTitre()
        couleurActive = workout.getCouleur()
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

    fun creerWorkout() {
        val newWorkout = Workout(listeJournees.toMutableSet(), couleurActive, inputTitre)
        entrainements.addWorkout(newWorkout)
    }

    fun updateWorkout() {
        workout!!.setJournees(listeJournees.toMutableSet())
        workout.setTitre(inputTitre)
        workout.setCouleurActive(couleurActive)
    }

    fun envoyerValeurs() {
        if (inputTitre.isEmpty()) {
            inputTitreHasError = true
            return
        }
        if (workout == null) {
            creerWorkout()
        } else {
            updateWorkout()
        }
        hasToSaveData = true
    }

    Page(appBar = AppBar(navController)
        .titre("Nouveau workout")
        .backButton(true)
        .popup(popup)
        .showPopupCondition({
            if (workout == null && (inputTitre.isNotEmpty() || couleurActive != Color.White || listeJournees.isNotEmpty())) {
                true
            } else if (workout != null && (inputTitre != workout.getTitre() || couleurActive != workout.getCouleur() || listeJournees.toSet() != workout.getJournees())) {
                true
            } else {
                false
            }
        })
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
                    titre = "Titre entraînement",
                    value = inputTitre,
                    onChange = {
                        inputTitre = it
                        inputTitreHasError = false
                    },
                    placeholder = "Entrez le titre",
                    isError = inputTitreHasError
                )
                Spacer(modifier = Modifier.height(20.dp))
                CheckJourSemaine(listeJournees)

                val controller = rememberColorPickerController()

                RoueDeCouleur(controller, couleurActive) { couleurActive = it.color }
                TextButton(
                    onClick = {
                        couleurActive = Color.White
                        controller.selectCenter(false)
                    },
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(1.dp, Color.LightGray),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text("Reset blanc", letterSpacing = 0.sp)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { envoyerValeurs() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (couleurActive == Color.White) Color.Black else couleurActive,
                    contentColor = if (couleurActive == Color.White) Color.White else getCouleurDependantBg(couleurActive)
                ),
                contentPadding = PaddingValues(all = 15.dp)
            ) {
                Text("Sauvegarder")
            }
        }
    }
}