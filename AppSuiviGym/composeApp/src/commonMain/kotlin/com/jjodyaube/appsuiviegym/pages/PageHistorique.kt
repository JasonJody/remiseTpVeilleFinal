package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.HistoriqueCard

@Composable
fun PageHistorique(navController: NavHostController) {

    val globalVariable = GlobalVariable.getInstance()
    val exercice = globalVariable.getCurrentExercice()
    if (exercice == null) {
        navController.popBackStack()
        return
    }

    val allSets = exercice.getAllSets()

    Page(
        AppBar(navController)
            .titre(exercice.getNom())
            .backButton(true),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Text("Dernier", letterSpacing = (-0.1).sp)
            //Avant dernier
            HistoriqueCard(allSets[allSets.size - 2])
            Spacer(modifier = Modifier.height(10.dp))
            Text("Premier", letterSpacing = (-0.1).sp)
            HistoriqueCard(allSets.first())

            Spacer(modifier = Modifier.height(30.dp))

            Text("Liste complète", letterSpacing = (-0.1).sp)
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (set in allSets.reversed().subList(1, allSets.size)) {
                    HistoriqueCard(set)
                }
            }

        }
    }
}