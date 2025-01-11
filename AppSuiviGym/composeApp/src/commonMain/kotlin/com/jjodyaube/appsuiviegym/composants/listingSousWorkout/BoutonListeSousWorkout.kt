package com.jjodyaube.appsuiviegym.composants.listingSousWorkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg
import com.jjodyaube.appsuiviegym.utils.getDarkerColor
import com.jjodyaube.appsuiviegym.utils.getPluriel
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowDown
import compose.icons.feathericons.ArrowUp
import compose.icons.feathericons.Settings

@Composable
fun BoutonListeSousWorkout(
    navController: NavController,
    entrainement: Structure,
    sousWorkout: SousWorkout,
    workout: Workout,
    sousWorkoutGotDeleted: MutableState<Boolean>,
    isUpdating: Boolean,
    listeSousWorkout: MutableState<MutableList<SousWorkout>>,
    onDelete: () -> Unit,
) {
    val globalVariable = GlobalVariable.getInstance()

    fun getBorderColor(sousWorkout: SousWorkout): Color {
        if (sousWorkout.getCouleur() == Color.White) {
            return Color.Black
        }

        return getDarkerColor(sousWorkout.getCouleur())
    }

    val showPopup = remember { mutableStateOf(false) }

    if (showPopup.value) {
        CustomAlertDialog(
            "Es-tu sur de vouloir supprimer: “${sousWorkout.getTitre()}” ?",
            "Supprimer",
            showPopup,
            {
                listeSousWorkout.value = listeSousWorkout.value.toMutableList().apply {
                    remove(sousWorkout)
                }
                workout.removeSousWorkout(sousWorkout)
                onDelete()
                sousWorkoutGotDeleted.value = true
                showPopup.value = false
            })
    }
    fun moveUpSousWorkout() {
        val index = listeSousWorkout.value.indexOf(sousWorkout)
        if (index > 0) {
            listeSousWorkout.value = listeSousWorkout.value.toMutableList().apply {
                add(index - 1, removeAt(index))
            }
            workout.moveUpSousWorkout(sousWorkout)
            sousWorkoutGotDeleted.value = true
        }
    }

    fun moveDownSousWorkout() {
        val index = listeSousWorkout.value.indexOf(sousWorkout)
        if (index < listeSousWorkout.value.size - 1) {
            listeSousWorkout.value = listeSousWorkout.value.toMutableList().apply {
                add(index + 1, removeAt(index))
            }
            workout.moveDownSousWorkout(sousWorkout)
            sousWorkoutGotDeleted.value = true
        }
    }


    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        val textColor = getCouleurDependantBg(sousWorkout.getCouleur())

        Box(modifier = Modifier.weight(1f)) {
            TextButton(
                onClick = {
                    val indexSouWorkout = workout.getIndexOfSousWorkout(sousWorkout)
                    globalVariable.setCurrentSousWorkout(indexSouWorkout)
                    navController.navigate(
                        "workout/exercices"
                    )
                },
                modifier = Modifier
                    .heightIn(100.dp)
                    .border(1.dp, getBorderColor(sousWorkout), RoundedCornerShape(5.dp))
                    .background(sousWorkout.getCouleur(), RoundedCornerShape(5.dp))
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Gray,
                    backgroundColor = Color.Transparent
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 20.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                    ) {

                        Text(
                            sousWorkout.getTitre(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal,
                            color = textColor,
                            letterSpacing = (-1).sp,
                            lineHeight = 30.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        if (sousWorkout.getNombreEntrainement() == 0) {
                            Text(
                                "Aucun entraînement",
                                color = textColor,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = (-1).sp
                            )
                        } else {
                            val nombreEntrainement = sousWorkout.getNombreEntrainement()
                            Text(
                                "$nombreEntrainement ${
                                    getPluriel(
                                        nombreEntrainement,
                                        "entraînement"
                                    )
                                }",
                                color = textColor,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = (-1).sp
                            )
                        }
                    }
                    if (isUpdating) {
                        Row(
                            modifier = Modifier.padding(end = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                IconButton(
                                    onClick = {
                                        showPopup.value = true
                                    },
                                    icon = Icons.Filled.Close,
                                    description = "Supprimer",
                                    contentColor = textColor
                                )
                                IconButton(
                                    onClick = {
                                        val indexWorkout = entrainement.getIndexOfWorkout(workout)
                                        val indexSousWorkout = workout.getIndexOfSousWorkout(sousWorkout)
                                        navController.navigate("creer/sous_workout/$indexWorkout/$indexSousWorkout")
                                    },
                                    icon = FeatherIcons.Settings,
                                    description = "Supprimer",
                                    contentColor = textColor
                                )
                            }
                            Column {
                                val index = listeSousWorkout.value.indexOf(sousWorkout)

                                if (index == 0) {
                                    Box(modifier = Modifier.height(40.dp))
                                } else {
                                    IconButton(
                                        onClick = {
                                            moveUpSousWorkout()
                                        },
                                        icon = FeatherIcons.ArrowUp,
                                        description = "Monter index",
                                        iconPadding = 1,
                                        contentColor = textColor
                                    )
                                }

                                if (index == listeSousWorkout.value.lastIndex) {
                                    Box(modifier = Modifier.height(40.dp))
                                } else {
                                    IconButton(
                                        onClick = {
                                            moveDownSousWorkout()
                                        },
                                        icon = FeatherIcons.ArrowDown,
                                        description = "Descendre index",
                                        iconPadding = 1,
                                        contentColor = textColor
                                    )
                                }
                            }
                        }
                    } else {
                        IconButton(
                            onClick = {
                                showPopup.value = true
                            },
                            contentColor = textColor,
                            icon = Icons.Filled.Close,
                            description = "Supprimer entraînement"
                        )
                    }
                }
            }
        }
    }
}