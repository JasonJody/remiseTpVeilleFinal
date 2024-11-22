package com.jjodyaube.appsuiviegym.composants.ListingSousWorkout

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
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.utils.capitalize
import com.jjodyaube.appsuiviegym.utils.getCouleurDependantBg
import com.jjodyaube.appsuiviegym.utils.getDarkerColor

@Composable
fun BoutonListeSousWorkout(
    navController: NavController,
    entrainement: Structure,
    sousWorkout: SousWorkout,
    workout: Workout,
    sousWorkoutGotDeleted: MutableState<Boolean>,
) {
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
                entrainement.removeSousWorkout(sousWorkout)
                sousWorkoutGotDeleted.value = true
                showPopup.value = false
            })
    }

    Box(modifier = Modifier.padding(10.dp)) {
        TextButton(
            onClick = {
                navController.navigate(
                    "workout/${entrainement.getIndexOfWorkout(workout)}/${workout.getIndexOfSousWorkout(sousWorkout)}"
                )
            },
            modifier = Modifier
                .height(100.dp)
                .border(1.dp, getBorderColor(sousWorkout), RoundedCornerShape(5.dp))
                .background(sousWorkout.getCouleur(), RoundedCornerShape(5.dp))
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Gray,
                backgroundColor = Color.Transparent
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val textColor = getCouleurDependantBg(sousWorkout.getCouleur())
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
                        letterSpacing = (-1).sp
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
                        val isMoreThanTen = sousWorkout.getNombreEntrainement() > 1
                        fun getS(): String {
                            if (isMoreThanTen) {
                                return "s"
                            } else {
                                return ""
                            }
                        }
                        Text(
                            "${sousWorkout.getNombreEntrainement()} entraînement${getS()}",
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