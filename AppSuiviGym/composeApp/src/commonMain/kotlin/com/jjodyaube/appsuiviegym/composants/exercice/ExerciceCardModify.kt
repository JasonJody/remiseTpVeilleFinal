package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Exercice
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.utils.getPluriel
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowDown
import compose.icons.feathericons.ArrowUp
import compose.icons.feathericons.Settings

private val horizontalPadding = 30

@Composable
fun ExerciceCardModify(
    navController: NavController,
    sousWorkout: SousWorkout,
    exercice: Exercice,
    exerciceGotModified: MutableState<Boolean>,
    listeExecices: MutableState<MutableList<Exercice>>,
    onDelete: () -> Unit
) {
    val showPopup = remember { mutableStateOf(false) }

    if (showPopup.value) {
        CustomAlertDialog(
            "Es-tu sur de vouloir supprimer: “${exercice.getNom()}” ?",
            "Supprimer",
            showPopup,
            {
                listeExecices.value = listeExecices.value.toMutableList().apply {
                    remove(exercice)
                }
                sousWorkout.removeExercice(exercice)
                onDelete()
                exerciceGotModified.value = true
                showPopup.value = false
            }
        )
    }

    fun moveUpExercice() {
        val index = listeExecices.value.indexOf(exercice)
        if (index > 0) {
            listeExecices.value = listeExecices.value.toMutableList().apply {
                add(index - 1, removeAt(index))
            }
            sousWorkout.moveUpExercice(exercice)
            exerciceGotModified.value = true
        }
    }

    fun moveDownExercice() {
        val index = listeExecices.value.indexOf(exercice)
        if (index < listeExecices.value.size - 1) {
            listeExecices.value = listeExecices.value.toMutableList().apply {
                add(index + 1, removeAt(index))
            }
            sousWorkout.moveDownExercice(exercice)
            exerciceGotModified.value = true
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(5.dp)
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(5.dp))
            .background(Color.White, shape = RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(horizontal = horizontalPadding.dp)
            ) {
                Text(
                    exercice.getNom(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 30.sp,
                    letterSpacing = (-1).sp,
                    lineHeight = 45.sp,
                )
                val nombreSet = exercice.getNombreSet()
                NombreSetEtNombreRep(exercice, nombreSet, 0)
            }
            Row(
                modifier = Modifier.padding(end = (horizontalPadding/2).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    IconButton(
                        onClick = {
                            showPopup.value = true
                        },
                        icon = Icons.Filled.Close,
                        description = "Supprimer",
                    )
                    IconButton(
                        onClick = {
                            navController.navigate("creer/exercice/${sousWorkout.getIndexOfExercice(exercice)}")
                        },
                        icon = FeatherIcons.Settings,
                        description = "Supprimer",
                    )
                }
                Column {
                    val index = listeExecices.value.indexOf(exercice)

                    if (index == 0) {
                        Box(modifier = Modifier.height(40.dp))
                    } else {
                        IconButton(
                            onClick = {
                                moveUpExercice()
                            },
                            icon = FeatherIcons.ArrowUp,
                            description = "Monter index",
                            iconPadding = 1
                        )
                    }

                    if (index == listeExecices.value.lastIndex) {
                        Box(modifier = Modifier.height(40.dp))
                    } else {
                        IconButton(
                            onClick = {
                                moveDownExercice()
                            },
                            icon = FeatherIcons.ArrowDown,
                            description = "Descendre index",
                            iconPadding = 1
                        )
                    }
                }
            }
        }
    }
}