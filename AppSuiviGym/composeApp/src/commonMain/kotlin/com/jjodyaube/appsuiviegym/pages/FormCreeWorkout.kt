package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjodyaube.appsuiviegym.Jours
import com.jjodyaube.appsuiviegym.Popup
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.CheckJourSemaine
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.RoueDeCouleur
import com.jjodyaube.appsuiviegym.saveEntrainements
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg

@Composable
fun FormCreeWorkout(
    navController: NavHostController,
    entrainements: Structure,
    nbWorkoutCreated: Int
) {

    val focusManager: FocusManager = LocalFocusManager.current

    val listeJournees = remember { mutableStateListOf<Jours>() }
    var inputTitre by remember { mutableStateOf("") }
    var inputTitreHasError by remember { mutableStateOf(false) }
    val showPopup = remember { mutableStateOf(false) }
    var couleurActive by remember { mutableStateOf(Color.Black) }

    var hasToSaveData by remember { mutableStateOf(false) }

    if (hasToSaveData) {
        saveEntrainements(entrainements)
//        nbWorkoutCreated.value += 1
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

    fun envoyerValeurs() {
        if (inputTitre.isEmpty()) {
            inputTitreHasError = true
            return
        }
        val newWorkout = Workout(listeJournees.toMutableSet(), couleurActive, inputTitre)
        entrainements.addWorkout(newWorkout)
        hasToSaveData = true
    }

    Page(appBar = AppBar(navController)
        .titre("Nouveau workout")
        .backButton(true)
        .popup(popup)
        .showPopupCondition({ !inputTitre.isEmpty() })
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
                Box (modifier = Modifier.fillMaxWidth()) {
                    Text("Titre entraînement")
                }
                OutlinedTextField(
                    value = inputTitre,
                    onValueChange = {
                        inputTitre = it
                        inputTitreHasError = false
                    },
                    placeholder = { Text("Entrez le titre",  color = Color.LightGray) },
                    label = null,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = inputTitreHasError,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.LightGray,
                        cursorColor = if (inputTitre.isEmpty()) Color.LightGray else Color.DarkGray,
                        focusedLabelColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.LightGray
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        },
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                CheckJourSemaine(listeJournees)
                RoueDeCouleur({ couleurActive = it.color })
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
                Text("Envoyer")
            }
        }
    }
}