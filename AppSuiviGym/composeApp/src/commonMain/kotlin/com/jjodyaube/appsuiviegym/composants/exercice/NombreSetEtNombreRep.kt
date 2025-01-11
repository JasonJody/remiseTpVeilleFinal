package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.Exercice
import com.jjodyaube.appsuiviegym.utils.getPluriel

@Composable
fun NombreSetEtNombreRep(
    exercice: Exercice,
    nombreSet: Int,
    horizontalPadding: Int
) {
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
}