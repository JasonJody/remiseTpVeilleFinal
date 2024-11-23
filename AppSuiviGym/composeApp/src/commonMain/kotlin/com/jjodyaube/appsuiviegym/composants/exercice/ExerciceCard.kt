package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.Exercice

@Composable
fun ExerciceCard(exercice: Exercice) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(5.dp))
            .background(Color.White, shape = RoundedCornerShape(5.dp))
    ) {
        Column(
            modifier = Modifier.padding(start = 25.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                    Text(
                        exercice.getNom() +
                                if (exercice.isDone()) " ✓" else "",
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        letterSpacing = (-1).sp,
                        lineHeight = 30.sp,
                        modifier = Modifier.weight(1f)
                    )
                Row {
                    TextButton(
                        onClick = {},
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            backgroundColor = Color.Transparent
                        )
                    ) {
                        Icon(Icons.Filled.Info, contentDescription = "Historique")
                    }
                    TextButton(
                        onClick = {},
                        modifier = Modifier.size(40.dp),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            backgroundColor = Color.Transparent
                        )
                    ) {
                        Icon(Icons.Filled.Close, contentDescription = "Supprimer")
                    }
                }
            }
            val isMoreThanTen = exercice.getNombreSet() > 1
            fun getS(): String {
                if (isMoreThanTen) {
                    return "s"
                } else {
                    return ""
                }
            }
            Text("${exercice.getNombreSet()} set${getS()}", color = Color.Gray, letterSpacing = (-1).sp)
        }
    }
}
