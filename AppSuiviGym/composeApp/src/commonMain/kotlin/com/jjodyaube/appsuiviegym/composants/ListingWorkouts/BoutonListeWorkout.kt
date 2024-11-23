package com.jjodyaube.appsuiviegym.composants.ListingWorkouts

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.jjodyaube.appsuiviegym.CurrentWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.utils.capitalize
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg
import com.jjodyaube.appsuiviegym.utils.getDarkerColor

@Composable
fun BoutonListeWorkout(
    navController: NavController,
    entrainement: Structure,
    workout: Workout,
    workoutGotDeleted: MutableState<Boolean>,
) {
    fun getBorderColor(workout: Workout): Color {
        if (workout.getCouleur() == Color.White) {
            return Color.Black
        }

        return getDarkerColor(workout.getCouleur())
    }

    val showPopup = remember { mutableStateOf(false) }

    if (showPopup.value) {
        CustomAlertDialog(
            "Es-tu sur de vouloir supprimer: “${workout.getTitre()}” ?",
            "Supprimer",
            showPopup,
            {
                entrainement.removeWorkout(workout)
                workoutGotDeleted.value = true
                showPopup.value = false
            })
    }

    Box(modifier = Modifier.padding(10.dp)) {
        TextButton(
            onClick = {
                CurrentWorkout.getInstance()
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
                val textColor = getCouleurDependantBg(workout.getCouleur())
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
                TextButton(
                    onClick = {
                        showPopup.value = true
                    },
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = textColor
                    )
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Supprimer entraînement"
                    )
                }
            }
        }
    }
}
