package com.jjodyaube.appsuiviegym.composants.ListingSousWorkout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
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
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg
import com.jjodyaube.appsuiviegym.utils.getDarkerColor
import com.jjodyaube.appsuiviegym.utils.getPluriel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown
import compose.icons.fontawesomeicons.solid.ArrowUp

@Composable
fun BoutonListeSousWorkout(
    navController: NavController,
    sousWorkout: SousWorkout,
    workout: Workout,
    sousWorkoutGotDeleted: MutableState<Boolean>,
    isUpdatingIndexPositions: Boolean,
    listeSousWorkout: MutableState<MutableList<SousWorkout>>,
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
        if (isUpdatingIndexPositions) {
            Column(
                modifier = Modifier.height(100.dp).padding(start = 5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextButton(
                    onClick = {
                        moveUpSousWorkout()
                    },
                    modifier = Modifier.border(1.dp, getBorderColor(sousWorkout), RoundedCornerShape(5.dp))
                        .background(sousWorkout.getCouleur(), RoundedCornerShape(5.dp))
                        .weight(1f)
                        .aspectRatio(1f/1f),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = textColor,
                        backgroundColor = sousWorkout.getCouleur()
                    )
                ) {
                    Icon(
                        FontAwesomeIcons.Solid.ArrowUp,
                        contentDescription = "Monter d'index",
                        modifier = Modifier.padding(7.dp)
                    )
                }
                TextButton(
                    onClick = {
                        moveDownSousWorkout()
                    },
                    modifier = Modifier.border(1.dp, getBorderColor(sousWorkout), RoundedCornerShape(5.dp))
                        .background(sousWorkout.getCouleur(), RoundedCornerShape(5.dp))
                        .weight(1f)
                        .aspectRatio(1f/1f),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = textColor,
                        backgroundColor = sousWorkout.getCouleur()
                    )
                ) {
                    Icon(
                        FontAwesomeIcons.Solid.ArrowDown,
                        contentDescription = "Descendre d'index",
                        modifier = Modifier.padding(7.dp)
                    )
                }
            }
        }
    }
}