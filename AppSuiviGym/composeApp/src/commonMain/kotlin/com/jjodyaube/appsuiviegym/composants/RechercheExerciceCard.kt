package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.GlobalVariable
import com.jjodyaube.appsuiviegym.Structure

@Composable
fun RechercheExerciceCard(
    entrainements: Structure,
    navController: NavController,
    globalVariable: GlobalVariable,
    titreExercice: String,
    suppressionIsActivated: Boolean,
    listExercicesGotModified: MutableState<Boolean>,
    listeTitres: MutableState<MutableList<String>>
) {
    fun supprimerExercice() {
        listeTitres.value = listeTitres.value.toMutableList().apply {
            remove(titreExercice)
        }
        listExercicesGotModified.value = true
        entrainements.deleteExeciceTitre(titreExercice)
    }
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 3.dp)
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(5.dp)
                )
                .background(Color.White, shape = RoundedCornerShape(5.dp))
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = {
                    globalVariable.setcreeExerciceRechercheTitreChoisi(
                        titreExercice
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Black
                ),
            ) {
                Text(
                    titreExercice,
                    modifier = Modifier.weight(1f)
                        .padding(start = 15.dp)
                        .padding(vertical = 10.dp),
                    letterSpacing = (-0.1).sp,
                    fontSize = 16.sp
                )
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Choisir cet exercice",
                    modifier = Modifier.padding(
                        horizontal = 15.dp,
                        vertical = 14.dp
                    )
                )
            }
        }
        if (suppressionIsActivated) {
            Box(
                modifier = Modifier
                    .clickable(onClick = {
                        supprimerExercice()
                    })
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(Color.White, shape = RoundedCornerShape(5.dp))
                    .fillMaxHeight()
                    .width(45.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Supprimer exercice",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

