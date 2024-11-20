package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.Rgb
import androidx.compose.ui.unit.dp
import com.jjodyaube.appsuiviegym.Jours

@Composable
fun CheckJourSemaine(listeJournees: SnapshotStateList<Jours>) {

    val totalJours = Jours.entries.size
    var i = 0

    fun capitalize(word: String) : String{
        return word.lowercase().replaceFirstChar { firstChar -> firstChar.uppercase() }
    }

    Column {
        while (i < totalJours) {
            val nbPerRow = 2
            var startingPos = i
            Row(
                modifier = Modifier.padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                for(j in startingPos..<startingPos + nbPerRow) {
                    if (i >= totalJours ) {
                        Box(
                            modifier = Modifier
                            .height(30.dp)
                            .weight(1f)
                        ) {}
                        continue
                    }
                    val jour = Jours.entries[i]

                    val borderColor = if (listeJournees.contains(jour)) Color.hsl(133f, 1f, 0.36f) else Color.Gray

                    OutlinedButton(
                        onClick = {
                            if (listeJournees.contains(jour)) {
                                listeJournees.remove(jour)
                            } else {
                                listeJournees.add(jour)
                            }
                        },
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(1.dp, borderColor),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(capitalize(jour.name))
                    }
                    i += 1
                }
                startingPos = i
            }
        }
    }
}