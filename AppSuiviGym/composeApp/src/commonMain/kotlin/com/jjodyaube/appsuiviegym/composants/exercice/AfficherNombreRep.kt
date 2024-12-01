package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.utils.getPluriel
import kotlin.math.absoluteValue

@Composable
fun AfficherNombreRep(nombeRepetition: Int, oldNombreRep: Int, sizeMultiplicateur: Float = 1f) {
    fun moreRepThanLastTime(): Boolean {
        return nombeRepetition > oldNombreRep
    }

    fun differenceRep(): String {
        val difference = (nombeRepetition - oldNombreRep).absoluteValue
        return difference.toString()
    }

    val density = LocalDensity.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            Text(
                nombeRepetition.toString(),
                color = Color.Black,
                fontSize = (20 * sizeMultiplicateur).sp
            )
                if (nombeRepetition != oldNombreRep) {
                    val diffRep = differenceRep()
                    var largeurDifference by remember { mutableStateOf(0.dp) }
                    Text(
                        (if (moreRepThanLastTime()) "+" else "-") + diffRep,
                        color = if (moreRepThanLastTime()) Color(0xFF18A82D) else Color.Red,
                        fontSize = (10 * sizeMultiplicateur).sp,
                        modifier = Modifier.onGloballyPositioned { coordonee ->
                                largeurDifference = with(density) { coordonee.size.width.toDp() }
                            }
                            .align(Alignment.TopEnd)
                            .offset(x = largeurDifference)
                    )
                }
        }
        Text(
            getPluriel(nombeRepetition, "Rep"),
            color = Color.Gray,
            fontSize = (11 * sizeMultiplicateur).sp,
            letterSpacing = (-0.1).sp
        )
    }
}