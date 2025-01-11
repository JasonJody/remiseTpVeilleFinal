package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.Action
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.ButtonActions
import com.jjodyaube.appsuiviegym.composants.exercice.ListExercices
import com.jjodyaube.appsuiviegym.composants.exercice.ListExercicesInModification
import com.jjodyaube.appsuiviegym.saveEntrainements
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import compose.icons.FeatherIcons
import compose.icons.feathericons.Check
import compose.icons.feathericons.Plus
import compose.icons.feathericons.Settings
import compose.icons.feathericons.File

@Composable
fun PageExercices(navController: NavController, entrainements: Structure) {
    val globalVariable = GlobalVariable.getInstance()

    if(
        globalVariable.getCurrentWorkout() == null
        || globalVariable.getCurrentSousWorkout() == null
        ) {
        navController.popBackStack()
        return
    }

    val newWorkoutSet = remember { mutableStateOf(false) }

    val delaiChargementDefault = 0.5
    var delaiChargement by remember { mutableStateOf(delaiChargementDefault) }
    val isUpdatingSets = remember { mutableStateOf(false) }

    var sousWorkout by remember { mutableStateOf<SousWorkout?>(null)}
    var hasExercice by remember { mutableStateOf(sousWorkout?.getExercices()?.isNotEmpty()) }

    fun onDelete() {
        hasExercice = sousWorkout!!.getExercices().isNotEmpty()
    }

    LaunchedEffect(Unit) {
        val workout = entrainements.getWorkoutsAt(globalVariable.getCurrentWorkout()!!)
        sousWorkout = workout.getSousWorkoutAt(globalVariable.getCurrentSousWorkout()!!)
        onDelete()
    }


    LaunchedEffect(newWorkoutSet.value) {
        if (!newWorkoutSet.value) {
            return@LaunchedEffect
        }

        while(delaiChargement >= 0) {
            delay(0.1.seconds)
            delaiChargement -= 0.1
        }
        delaiChargement = 0.0
    }

    if (newWorkoutSet.value && delaiChargement <= 0.0) {
        saveEntrainements(entrainements)
        delaiChargement = delaiChargementDefault
        newWorkoutSet.value = false
    }

    fun terminerSession() {
        sousWorkout!!.done()
        newWorkoutSet.value = true
    }

    Page(AppBar(navController)
        .titre(if (sousWorkout == null) "Chargement" else sousWorkout!!.getTitre())
        .backButton(true)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (!newWorkoutSet.value && sousWorkout != null) {
                    if (isUpdatingSets.value) {
                        ListExercicesInModification(
                            navController,
                            entrainements,
                            sousWorkout!!,
                            { onDelete() }
                        )
                    } else {
                        ListExercices(
                            navController,
                            entrainements,
                            sousWorkout!!,
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Chargement des exercices")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(align = Alignment.BottomEnd)
            ) {
                if(hasExercice == null) {
                    return@Box
                }

                val actionButtonAdd = {
                    Action(
                        icon = FeatherIcons.Plus,
                        action = { navController.navigate("creer/exercice/-1") },
                        description = "Ajouter workout"
                    )
                }

                if (!hasExercice!!) {
                    ButtonActions()
                        .action(
                            actionButtonAdd()
                        )
                        .build()
                    return@Box
                }

                if (!isUpdatingSets.value) {
                    ButtonActions()
                        .action(
                            Action(
                                icon = FeatherIcons.File,
                                action = { terminerSession() },
                                description = "Activer la mise à jour"
                            )
                        )
                        .action(
                            Action(
                                icon = FeatherIcons.Settings,
                                action = { isUpdatingSets.value = !isUpdatingSets.value },
                                description = "Activer la mise à jour"
                            )
                        )
                        .action(
                            actionButtonAdd()
                        )
                        .build()
                } else {
                    ButtonActions()
                        .action(
                            Action(
                                icon = FeatherIcons.Check,
                                action = { isUpdatingSets.value = !isUpdatingSets.value },
                                description = "Désactiver la mise à jour"
                            )
                        )
                        .build()
                }
            }
        }
    }
}