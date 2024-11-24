package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
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
import com.jjodyaube.appsuiviegym.Exercice
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.utils.getPluriel

private val horizontalPadding = 25

@Composable
fun ExerciceCard(
    entrainement: Structure,
    sousWorkout: SousWorkout,
    exercice: Exercice,
    exerciceGotDeleted: MutableState<Boolean>,
) {
    val showPopup = remember { mutableStateOf(false) }
    val exerciceIsDone = remember { mutableStateOf(false) }

    fun checkIfExerciceIsDone() {
        exerciceIsDone.value = exercice.isDone()
    }

    if (showPopup.value) {
        CustomAlertDialog(
            "Es-tu sur de vouloir supprimer: “${exercice.getNom()}” ?",
            "Supprimer",
            showPopup,
            {
                sousWorkout.removeExercice(exercice)
                exerciceGotDeleted.value = true
                showPopup.value = false
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(5.dp))
            .background(Color.White, shape = RoundedCornerShape(5.dp))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
        ) {
            Row(
                modifier = Modifier.padding(start = horizontalPadding.dp, end = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                    Text(
                        exercice.getNom() +
                                if (exerciceIsDone.value) " ✓" else "",
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        letterSpacing = (-1).sp,
                        lineHeight = 30.sp,
                        modifier = Modifier.weight(1f)
                    )
                Row {
                    IconButton(
                        onClick = {},
                        icon = Icons.Filled.Info,
                        description = "Historique"
                    )
                    IconButton(
                        onClick = {
                            showPopup.value = true
                        },
                        icon = Icons.Filled.Close,
                        description = "Supprimer"
                    )
                }
            }
            val nombreSet = exercice.getNombreSet()
            Text(
                "$nombreSet ${getPluriel(nombreSet, "set")}",
                color = Color.Gray,
                letterSpacing = (-1).sp,
                modifier = Modifier.padding(horizontal = horizontalPadding.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            ListeDesSets(entrainement, exercice.getCurrentSets(), horizontalPadding
            ) { checkIfExerciceIsDone() }
        }
    }
}