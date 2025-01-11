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
import com.jjodyaube.appsuiviegym.composants.BoutonAjoutModifier
import com.jjodyaube.appsuiviegym.composants.listingWorkouts.ListWorkouts

@Composable
fun HomePage(
    navController: NavController,
    entrainement: Structure,
) {

    val isUpdating = remember { mutableStateOf(false) }
    var hasSousWorkout by remember { mutableStateOf(entrainement.getWorkouts().isNotEmpty()) }

    fun onDelete() {
        hasSousWorkout = entrainement.getWorkouts().isNotEmpty()
    }

    Page(
        appBar = AppBar(navController)
            .titre("Workouts")
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
                hasSousWorkout,
                isUpdating,
                "creer/workout/-1"

            )
        }
    }
}