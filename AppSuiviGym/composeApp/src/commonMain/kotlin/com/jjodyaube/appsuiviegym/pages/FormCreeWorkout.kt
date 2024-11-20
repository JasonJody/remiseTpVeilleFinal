package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jjodyaube.appsuiviegym.Jours
import com.jjodyaube.appsuiviegym.Popup
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.CheckJourSemaine
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog



@Composable
fun FormCreeWorkout(navController: NavHostController, entrainements: Structure) {


    val listeJournees = remember { mutableStateListOf<Jours>() }
    var inputTitre by remember { mutableStateOf("") }
    var inputTitreHasError by remember { mutableStateOf(false) }
    val showPopup = remember { mutableStateOf(false) }

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
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier
                        .fillMaxWidth().fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = inputTitre,
                        onValueChange = {
                            inputTitre = it
                            inputTitreHasError = false
                        },
                        label = { Text("Titre entraînement") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        isError = inputTitreHasError,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckJourSemaine(listeJournees)
                }
                Button(
                    onClick = { envoyerValeurs() },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
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
}