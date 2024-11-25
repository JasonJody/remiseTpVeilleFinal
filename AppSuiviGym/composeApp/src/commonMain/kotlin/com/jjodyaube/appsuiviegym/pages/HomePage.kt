package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.ExtendedMenuItem
import com.jjodyaube.appsuiviegym.composants.FloatingBtnAdd
import com.jjodyaube.appsuiviegym.composants.ListingWorkouts.ListWorkouts

@Composable
fun HomePage(
    navController: NavController,
    entrainement: Structure,
) {

    var isUpdatingIndexPositions by remember { mutableStateOf(false) }

    Page(
        appBar = AppBar(navController)
            .titre("Workouts")
            .addExtendedMenuItem(ExtendedMenuItem(
                "${if (isUpdatingIndexPositions) "Désactiver" else "Activer"} modification de l'ordre"
            ) { isUpdatingIndexPositions = !isUpdatingIndexPositions })
            .extendedMenuOffset(-15)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                ListWorkouts(navController, entrainement, isUpdatingIndexPositions)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                FloatingBtnAdd(navController, "cree/workout")
            }
        }
    }
}