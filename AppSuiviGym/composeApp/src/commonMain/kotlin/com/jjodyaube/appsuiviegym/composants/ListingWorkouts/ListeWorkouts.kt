package com.jjodyaube.appsuiviegym.composants.ListingWorkouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListWorkouts(
    navController: NavController,
    entrainement: Structure,
) {
    val workoutGotDeleted = remember { mutableStateOf(false) }

    if (workoutGotDeleted.value) {
        saveEntrainements(entrainement)
        workoutGotDeleted.value = false
    }

    if (entrainement.getWorkouts().isEmpty()) {
        Text("Aucun Entraînements")
        return
    }

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(entrainement.getWorkouts()) { workout ->
            BoutonListeWorkout(navController, entrainement, workout, workoutGotDeleted)
        }
    }
}