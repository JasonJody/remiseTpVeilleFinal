package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.WorkoutSet

@Composable
fun ModifySetButton(
    numeroDuSet: Int,
    set: WorkoutSet,
    horizontalPadding: Int,
    aSetIsBeingModified: MutableState<WorkoutSet?>
) {
    TextButton(
        onClick = { aSetIsBeingModified.value = set },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.LightGray
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.heightIn(50.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = horizontalPadding.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Set $numeroDuSet ${if (set.isDone() || set.isSkipped()) "⠀✓" else "ㅤ⠀"}",
                fontSize = 18.sp,
                color = Color.Gray,
                letterSpacing = (-0.5).sp
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                AfficherNombreRep(set.getNombreRepetition(), set.getOldNombreRepetition())
            }
            AfficherPoids(set)
        }
    }
}