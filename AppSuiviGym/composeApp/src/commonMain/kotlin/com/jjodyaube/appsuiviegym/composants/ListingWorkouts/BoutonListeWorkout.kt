package com.jjodyaube.appsuiviegym.composants.ListingWorkouts

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
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.utils.capitalize
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg
import com.jjodyaube.appsuiviegym.utils.getDarkerColor
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown
import compose.icons.fontawesomeicons.solid.ArrowUp

@Composable
fun BoutonListeWorkout(
    navController: NavController,
    entrainement: Structure,
    workout: Workout,
    workoutGotModified: MutableState<Boolean>,
    isUpdatingIndexPositions: Boolean,
    listeWorkout: MutableState<MutableList<Workout>>,
) {
    fun getBorderColor(workout: Workout): Color {
        if (workout.getCouleur() == Color.White) {
            return Color.Black
        }

        return getDarkerColor(workout.getCouleur())
    }

    fun moveUpWorkout() {
        val index = listeWorkout.value.indexOf(workout)
        if (index > 0) {
            listeWorkout.value = listeWorkout.value.toMutableList().apply {
                add(index - 1, removeAt(index))
            }
            entrainement.moveUpWorkout(workout)
            workoutGotModified.value = true
        }
    }

    fun moveDownWorkout() {
        val index = listeWorkout.value.indexOf(workout)
        if (index < listeWorkout.value.size - 1) {
            listeWorkout.value = listeWorkout.value.toMutableList().apply {
                add(index + 1, removeAt(index))
            }
            entrainement.moveDownWorkout(workout)
            workoutGotModified.value = true
        }
    }
    val showPopup = remember { mutableStateOf(false) }

    if (showPopup.value) {
        CustomAlertDialog(
            "Es-tu sur de vouloir supprimer: “${workout.getTitre()}” ?",
            "Supprimer",
            showPopup,
            {
                listeWorkout.value = listeWorkout.value.toMutableList().apply {
                    remove(workout)
                }
                entrainement.removeWorkout(workout)
                workoutGotModified.value = true
                showPopup.value = false
            })
    }

    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        val textColor = getCouleurDependantBg(workout.getCouleur())

        Box(modifier = Modifier.weight(1f)) {
            TextButton(
                onClick = {
                    GlobalVariable.getInstance()
                        .setCurrentWorkout(entrainement.getIndexOfWorkout(workout))
                    navController.navigate("workout")
                },
                modifier = Modifier
                    .heightIn(min = 100.dp)
                    .border(1.dp, getBorderColor(workout), RoundedCornerShape(5.dp))
                    .background(workout.getCouleur(), RoundedCornerShape(5.dp))
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
                            workout.getTitre(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal,
                            color = textColor,
                            letterSpacing = (-1).sp,
                            lineHeight = 30.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        if (workout.getJournees().isEmpty()) {
                            Text(
                                "Pas spécifié",
                                color = textColor,
                                fontWeight = FontWeight.Normal,
                                letterSpacing = (-1).sp
                            )
                        } else {
                            Text(
                                capitalize(workout.getJournees().joinToString(" / ")),
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
                        moveUpWorkout()
                    },
                    modifier = Modifier.border(1.dp, getBorderColor(workout), RoundedCornerShape(5.dp))
                        .background(workout.getCouleur(), RoundedCornerShape(5.dp))
                        .weight(1f)
                        .aspectRatio(1f/1f),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = textColor,
                        backgroundColor = workout.getCouleur()
                    )
                ) {
                    Icon(FontAwesomeIcons.Solid.ArrowUp,
                        contentDescription = "Monter d'index",
                        modifier = Modifier.padding(7.dp)
                    )
                }
                TextButton(
                    onClick = {
                        moveDownWorkout()
                    },
                    modifier = Modifier.border(1.dp, getBorderColor(workout), RoundedCornerShape(5.dp))
                        .background(workout.getCouleur(), RoundedCornerShape(5.dp))
                        .weight(1f)
                        .aspectRatio(1f/1f),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = textColor,
                        backgroundColor = workout.getCouleur()
                    )
                ) {
                    Icon(FontAwesomeIcons.Solid.ArrowDown,
                        contentDescription = "Descendre d'index",
                        modifier = Modifier.padding(7.dp)
                    )
                }
            }
        }
    }
}
