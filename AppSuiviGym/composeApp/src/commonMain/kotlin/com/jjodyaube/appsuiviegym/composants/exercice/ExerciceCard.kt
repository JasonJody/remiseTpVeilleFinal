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
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.utils.getPluriel
import compose.icons.FeatherIcons
import compose.icons.feathericons.Clock

private const val horizontalPadding = 25

@Composable
fun ExerciceCard(
    navController: NavController,
    entrainement: Structure,
    sousWorkout: SousWorkout,
    exercice: Exercice,
    sounVibrationIsEnable: MutableState<Boolean>,
) {
    val exerciceIsDone = remember { mutableStateOf(exercice.isDone()) }

    fun checkIfExerciceIsDone() {
        exerciceIsDone.value = exercice.isDone()
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
                IconButton(
                    onClick = {
                        val globalVariable = GlobalVariable.getInstance()
                        globalVariable.setCurrentExercice(sousWorkout.getIndexOfExercice(exercice))
                        navController.navigate("historique")
                    },
                    icon = FeatherIcons.Clock,
                    description = "Historique"
                )
            }
            val nombreSet = exercice.getNombreSet()
            Row {
                Text(
                    "$nombreSet ${getPluriel(nombreSet, "set")}",
                    color = Color.Gray,
                    letterSpacing = (-1).sp,
                    modifier = Modifier.padding(start = horizontalPadding.dp)
                )
                if (exercice.hasRep()) {
                    Text("/",
                        color = Color.Gray,
                        letterSpacing = (-1).sp,
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                    val minRep = exercice.getMinRep()
                    val maxRep = exercice.getMaxRep()
                    if (minRep != null) {
                        Text(minRep.toString(),
                            color = Color.Gray,
                            letterSpacing = (-1).sp)
                        if (maxRep != null) {
                            Text(" - ",
                                color = Color.Gray,
                                letterSpacing = (-1).sp)
                            Text(maxRep.toString(),
                                color = Color.Gray,
                                letterSpacing = (-1).sp)
                        }
                        val hasMaxRep = maxRep != null
                        val rep = " rep"
                        Text(
                            if (hasMaxRep)
                                getPluriel(maxRep!!.toInt(), rep)
                            else
                                getPluriel(minRep.toInt(), rep),
                            color = Color.Gray,
                            letterSpacing = (-1).sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            ListeDesSets(entrainement, exercice.getCurrentSets(), horizontalPadding, sounVibrationIsEnable
            ) { checkIfExerciceIsDone() }
        }
    }
}