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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.Exercice
import com.jjodyaube.appsuiviegym.SousWorkout
import com.jjodyaube.appsuiviegym.composants.CustomAlertDialog
import com.jjodyaube.appsuiviegym.composants.IconButton
import com.jjodyaube.appsuiviegym.composants.InputsAvecTitre
import com.jjodyaube.appsuiviegym.utils.getPluriel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.ArrowAltCircleDown
import compose.icons.fontawesomeicons.regular.ArrowAltCircleUp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private val horizontalPadding = 20

// TODO : verification de champs
// TODO : ajout description
// TODO : Modification des 2 autres pages

@Composable
fun ExerciceCardModify(
    sousWorkout: SousWorkout,
    exercice: Exercice,
    exerciceGotModified: MutableState<Boolean>,
    listeExecices: MutableState<MutableList<Exercice>>,
) {
    val showPopup = remember { mutableStateOf(false) }
    var tempNom by remember { mutableStateOf(exercice.getNom()) }
    var tempNomHasError by remember { mutableStateOf(false) }
    var tempNombreSet by remember { mutableStateOf(exercice.getNombreSet().toString()) }
    var tempNombreSetHasError by remember { mutableStateOf(false) }
    var timerBeforeSave by remember { mutableStateOf(0L) }
    var timerTrigger by remember { mutableStateOf(false) }
    val timerBeginningValue = 500L

    LaunchedEffect(exercice) {
        tempNom = exercice.getNom()
        tempNombreSet = exercice.getNombreSet().toString()
    }

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
                exerciceGotModified.value = true
                showPopup.value = false
            }
        )
    }

    fun saveChamps() {
        exercice.setNom(tempNom)
        exercice.setNombreDeSet(tempNombreSet.toInt())
        timerBeforeSave = 0
        exerciceGotModified.value = true
    }

    fun saveChampsQuandSwitchIndex() {
        exercice.setNom(tempNom)
        exercice.setNombreDeSet(tempNombreSet.toInt())
        timerBeforeSave = -10
    }

    fun moveUpSousWorkout() {
        val index = listeExecices.value.indexOf(exercice)
        if (index > 0) {
            listeExecices.value = listeExecices.value.toMutableList().apply {
                add(index - 1, removeAt(index))
            }
            sousWorkout.moveUpExercice(exercice)
            saveChampsQuandSwitchIndex()
            exerciceGotModified.value = true
        }
    }

    fun moveDownSousWorkout() {
        val index = listeExecices.value.indexOf(exercice)
        if (index < listeExecices.value.size - 1) {
            listeExecices.value = listeExecices.value.toMutableList().apply {
                add(index + 1, removeAt(index))
            }
            sousWorkout.moveDownExercice(exercice)
            saveChampsQuandSwitchIndex()
            exerciceGotModified.value = true
        }
    }

    LaunchedEffect(timerBeforeSave, timerTrigger) {
        if (timerBeforeSave > 0) {
            delay(100L)
            timerBeforeSave -= 100L
        }
        else if (timerBeforeSave <= -10L) {
            return@LaunchedEffect
        }
        else if ((!tempNomHasError && !tempNombreSetHasError) &&
            (tempNom != exercice.getNom()
            || tempNombreSet.toInt() != exercice.getNombreSet())
        ) {
            saveChamps()
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
                InputsAvecTitre(
                    value = tempNom,
                    onChange = {
                        tempNom = it
                        timerBeforeSave = timerBeginningValue
                        timerTrigger = !timerTrigger

                        tempNomHasError = tempNom.isEmpty()
                    },
                    placeholder = "Nom de l'exercice",
                    isError = tempNomHasError
                )
                InputsAvecTitre(
                    value = tempNombreSet,
                    onChange = {
                        tempNombreSet = it
                        timerBeforeSave = timerBeginningValue
                        timerTrigger = !timerTrigger

                        val valeurEnIntOuNull = tempNombreSet.toIntOrNull()
                        tempNombreSetHasError = tempNombreSet.isEmpty() || valeurEnIntOuNull == null
                    },
                    placeholder = "Nombre de sets",
                    isError = tempNombreSetHasError
                )
            }
            Row(
                modifier = Modifier.padding(end = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        showPopup.value = true
                    },
                    icon = Icons.Filled.Close,
                    description = "Supprimer",
                )
                Column {
                    IconButton(
                        onClick = {
                            moveUpSousWorkout()
                        },
                        icon = FontAwesomeIcons.Regular.ArrowAltCircleUp,
                        description = "Monter index",
                        modifier = Modifier.padding(1.dp)
                    )

                    IconButton(
                        onClick = {
                            moveDownSousWorkout()
                        },
                        icon = FontAwesomeIcons.Regular.ArrowAltCircleDown,
                        description = "Descendre index",
                        modifier = Modifier.padding(1.dp)
                    )
                }
            }
        }
    }
}