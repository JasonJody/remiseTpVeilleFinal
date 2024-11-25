package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.ExtendedMenuItem
import com.jjodyaube.appsuiviegym.composants.InputsAvecTitre
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
    var valeurAChercher by remember { mutableStateOf("") }

    fun getEntrainementAChercher(): List<String> {
        val listeExercicesTitre = entrainements.getExerciceTitres()
        if (valeurAChercher.isNotEmpty()) {
            return listeExercicesTitre.filter { (it.lowercase()).contains(valeurAChercher.lowercase()) }
        }

        return listeExercicesTitre.sorted()
    }

    Page(appBar = AppBar(navController)
        .titre("Rechercher un\nexercice")
        .backButton(true)
        .addExtendedMenuItem(ExtendedMenuItem("${if (suppressionIsActivated) "Désactiver" else "Activer"} suppression des execices")
        {
            suppressionIsActivated = !suppressionIsActivated
        })
        .extendedMenuOffset(-15)
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
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    for (titreExercice in getEntrainementAChercher()) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .border(
                                        BorderStroke(1.dp, Color.LightGray),
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .background(Color.White, shape = RoundedCornerShape(5.dp))
                                    .weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = {
                                        globalVariable.setcreeExerciceRechercheTitreChoisi(
                                            titreExercice
                                        )
                                        navController.popBackStack()
                                    },
                                    modifier = Modifier.weight(1f)
                                        .height(45.dp)
                                        .fillMaxWidth(),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent,
                                        contentColor = Color.Black
                                    ),
                                ) {
                                    Text(
                                        titreExercice,
                                        modifier = Modifier.weight(1f)
                                            .padding(start = 15.dp),
                                        letterSpacing = (-0.1).sp,
                                        fontSize = 16.sp
                                    )
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = "Choisir cet exercice",
                                        modifier = Modifier.padding(
                                            horizontal = 15.dp,
                                            vertical = 14.dp
                                        )
                                    )
                                }
                            }
                            if (suppressionIsActivated) {
                                TextButton(
                                    onClick = {
                                        listExercicesGotModified.value = true
                                        entrainements.deleteExeciceTitre(titreExercice)
                                    },
                                    modifier = Modifier
                                        .height(45.dp)
                                        .border(
                                            BorderStroke(1.dp, Color.LightGray),
                                            shape = RoundedCornerShape(5.dp)
                                        )
                                        .background(Color.White, shape = RoundedCornerShape(5.dp))
                                        .padding(0.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.Transparent,
                                        contentColor = Color.Black
                                    )
                                ) {
                                    Icon(
                                        Icons.Filled.Close,
                                        contentDescription = "Supprimer exercice",
                                        tint = Color.Black,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            TextButton(
                onClick = { valeurAChercher = inputRecherche },
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