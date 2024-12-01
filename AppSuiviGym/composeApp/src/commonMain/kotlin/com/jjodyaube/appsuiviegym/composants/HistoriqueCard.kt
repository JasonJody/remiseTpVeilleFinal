package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jjodyaube.appsuiviegym.TypeEquipement
import com.jjodyaube.appsuiviegym.UniteDeMesure
import com.jjodyaube.appsuiviegym.WorkoutSet
import com.jjodyaube.appsuiviegym.utils.getPluriel

@Composable
fun HistoriqueCard(sets: HashMap<Int, WorkoutSet>) {

    fun isEndingWithPointZero(number: Float): Boolean {
        return number == number.toInt().toFloat()
    }

    fun getPoidsPlateFormatee(nbPlate: Float, nbKgSupp: Float, uniteDeMesure: UniteDeMesure): String {
        //1 plate 12.5kg
        //2 plates 12.5kg
        //3 plates
        var result = ""
        if (nbPlate > 0) {
            result += "${nbPlate.toInt()} ${getPluriel(nbPlate.toInt(), "plate")} "
        }
        if (nbKgSupp > 0) {
            result += if (isEndingWithPointZero(nbKgSupp)) nbKgSupp.toInt() else nbKgSupp
            result += uniteDeMesure.toString().lowercase()
        }
        return result
    }

    fun getPoidsFormattee(set: WorkoutSet): String {
        return if (set.getTypeEquipement() == TypeEquipement.PLATES) {
            val (nbPlate, nbKgSupp) = set.getNombrePlaqueEtKgSupplementaire()
            getPoidsPlateFormatee(nbPlate, nbKgSupp, set.getUniteDeMesure())
        } else {
            val poids = set.getPoids()
            "${if (isEndingWithPointZero(poids)) poids.toInt() else poids}${set.getUniteDeMesure().toString().lowercase()}"
        }
    }

    // Retourne le poids qui est le plus présent dans les sets
    fun getMainPoids(): String? {
        val popularitePoid = mutableMapOf<String, Int>()

        for ((_, set) in sets) {
            val nomFormatee = getPoidsFormattee(set)
            popularitePoid[nomFormatee] = popularitePoid.getOrPut(nomFormatee) { 0 } + 1
        }

        return popularitePoid.maxByOrNull { it.value }?.key
    }

    val mainPoids = getMainPoids().orEmpty()

    fun getAllReps(): String {
        var resultAllRepsFormatted = ""
        for ((_, set) in sets) {
            resultAllRepsFormatted += if (set.isDone()) {
                    set.getNombreRepetition()
                } else if (set.isSkipped()) {
                    "X"
                } else if (!set.isSkipped() && !set.isDone()) {
                    "?"
                } else {
                    "..."
                }
            val poidsFormattee = getPoidsFormattee(set)
            if (poidsFormattee != mainPoids) {
                resultAllRepsFormatted += " ($poidsFormattee)"
            }
            resultAllRepsFormatted += " - "
        }
        resultAllRepsFormatted = resultAllRepsFormatted.substring(0, resultAllRepsFormatted.length - 3)

        return resultAllRepsFormatted
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .background(Color.White, shape = RoundedCornerShape(5.dp))
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(5.dp))
    ) {
        Row (
            modifier = Modifier.padding(10.dp)
        ){
            Text("$mainPoids: ${getAllReps()}")
        }
    }

}

