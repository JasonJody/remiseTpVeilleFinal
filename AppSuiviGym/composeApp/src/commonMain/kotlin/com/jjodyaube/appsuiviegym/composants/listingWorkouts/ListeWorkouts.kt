package com.jjodyaube.appsuiviegym.composants.listingWorkouts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListWorkouts(
    navController: NavController,
    entrainement: Structure,
    isUpdating: Boolean,
) {
    val workoutGotModified = remember { mutableStateOf(false) }

    if (workoutGotModified.value) {
        saveEntrainements(entrainement)
        workoutGotModified.value = false
    }

    if (entrainement.getWorkouts().isEmpty()) {
        Text("Aucun Entraînements")
        return
    }

    val listeWorkout = remember { mutableStateOf(entrainement.getWorkouts()) }

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        items(listeWorkout.value) { workout ->
            BoutonListeWorkout(
                navController,
                entrainement,
                workout,
                workoutGotModified,
                isUpdating,
                listeWorkout
            )
        }
        item { Spacer(Modifier.height(120.dp)) }
    }
}