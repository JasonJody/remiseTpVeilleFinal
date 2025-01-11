package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.InputsAvecTitre
import com.jjodyaube.appsuiviegym.composants.RechercheExerciceCard
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun PageRechercheExercice(
    navController: NavHostController,
    entrainements: Structure
) {


    val globalVariable = GlobalVariable.getInstance()

    if(globalVariable.getCurrentWorkout() == null) {
        navController.popBackStack()
        return
    }

    val listExercicesGotModified = remember { mutableStateOf(false) }
    var suppressionIsActivated by remember { mutableStateOf(false) }

    if (listExercicesGotModified.value) {
        saveEntrainements(entrainements)
        listExercicesGotModified.value = false
    }

    var inputRecherche by remember { mutableStateOf("") }

    Page(appBar = AppBar(navController)
        .titre("Rechercher un\nexercice")
        .backButton(true)
//        .addExtendedMenuItem(ExtendedMenuItem("${if (suppressionIsActivated) "Désactiver" else "Activer"} suppression des execices")
//        {
//            suppressionIsActivated = !suppressionIsActivated
//        })
//        .extendedMenuOffset(-15)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                InputsAvecTitre(
                    titre = "Recherche",
                    value = inputRecherche,
                    onChange = {
                        inputRecherche = it
                    },
                    placeholder = "Rechercher un exercice",
                )
            }
            Column(
                Modifier
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val listeTitres = remember { mutableStateOf(entrainements.getExerciceTitres().toMutableList()) }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(listeTitres.value) { titreExercice ->
                        if (titreExercice.lowercase().contains(inputRecherche.lowercase())) {
                            RechercheExerciceCard(
                                entrainements = entrainements,
                                navController = navController,
                                globalVariable = globalVariable,
                                titreExercice = titreExercice,
                                suppressionIsActivated = suppressionIsActivated,
                                listExercicesGotModified = listExercicesGotModified,
                                listeTitres = listeTitres
                            )
                        }
                    }
                }
            }
        }
    }
}