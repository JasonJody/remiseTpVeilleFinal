package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import com.jjodyaube.appsuiviegym.composants.BoutonAjoutModifier
import com.jjodyaube.appsuiviegym.composants.listingWorkouts.ListWorkouts
import compose.icons.TablerIcons

@Composable
fun HomePage(
    navController: NavController,
    entrainement: Structure,
) {

    val isUpdating = remember { mutableStateOf(false) }
    var hasWorkout by remember { mutableStateOf(entrainement.getWorkouts().isNotEmpty()) }

    fun onDelete() {
        hasWorkout = entrainement.getWorkouts().isNotEmpty()
    }

    Page(
        appBar = AppBar(navController)
            .titre("Workouts")
            .actionButtonIcon(Icons.Filled.AccountCircle)
            .actionButtonDescription("Voir crédits du créateur")
            .actionButtonAction { navController.navigate("credit") }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    ListWorkouts(navController, entrainement, isUpdating.value, { onDelete() })
                }
            }

            BoutonAjoutModifier(
                navController,
                hasWorkout,
                isUpdating,
                "creer/workout/-1"

            )
        }
    }
}