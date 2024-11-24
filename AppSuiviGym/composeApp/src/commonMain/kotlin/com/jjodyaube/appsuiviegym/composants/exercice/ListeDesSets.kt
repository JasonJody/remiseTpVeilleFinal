package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.WorkoutSet
import com.jjodyaube.appsuiviegym.jouerSonSuccess
import com.jjodyaube.appsuiviegym.jouerVibration
import com.jjodyaube.appsuiviegym.saveEntrainements

@Composable
fun ListeDesSets(
    entrainement: Structure,
    currentSet: HashMap<Int, WorkoutSet>,
    horizontalPadding: Int,
    sounVibrationIsEnable: MutableState<Boolean>,
    checkIfExerciceIsDone: () -> Unit
) {
    val aSetIsBeingModified: MutableState<WorkoutSet?> = remember { mutableStateOf(null) }
    val aSetGotModified = remember { mutableStateOf(false) }

    if (aSetGotModified.value) {
        saveEntrainements(entrainement)
        aSetGotModified.value = false
        checkIfExerciceIsDone()
        aSetIsBeingModified.value = null
        if (sounVibrationIsEnable.value) {
            jouerSonSuccess()
            jouerVibration(500)
        }
    }

    fun isMySetBeingModified(set: WorkoutSet): Boolean {
        return aSetIsBeingModified.value != null && set == aSetIsBeingModified.value
    }

    for ((key, set) in currentSet) {
        if (isMySetBeingModified(set)) {
            ModifySet(key, set, horizontalPadding, aSetIsBeingModified, aSetGotModified)
        } else {
            ModifySetButton(key, set, horizontalPadding, aSetIsBeingModified)
        }
    }
}
