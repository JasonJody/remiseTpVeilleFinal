package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.Workout

@Composable
fun ListWorkouts(entrainement: Structure) {

    fun getBorderColor(workout: Workout): Color {
        if (workout.getCouleur() == Color.White) {
            return Color.Black
        } else {
            return Color.Transparent
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        for (workout in entrainement.getWorkouts()) {
            Box(modifier = Modifier.padding(10.dp)) {
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .border(1.dp, getBorderColor(workout) , RoundedCornerShape(5.dp))
                        .background(workout.getCouleur())
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Élément ${workout.getTitre()}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        if (workout.getJournees().isEmpty()) {
                            Text("Pas spécifié")
                        } else {
                            Text(workout.getJournees().joinToString(" / "))
                        }
                    }
                }
            }
        }
    }
}