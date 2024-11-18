package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController

@Composable
fun PagePopup(navController: NavController) {
    var showPopup by remember { mutableStateOf(false) }

    if (showPopup) {
        AlertDialog(
            onDismissRequest = { showPopup = false }, // Fermer le popup sans action
            title = { Text("Sauvegarde perdu") },
            text = { Text("Aucune donnée sera sauvegarder\nÊtes-vous sûr de vouloir quitter ?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showPopup = false
                        navController.popBackStack() // Effectuer le retour
                    }
                ) {
                    Text("Quitter")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showPopup = false } // Annuler le retour
                ) {
                    Text("Rester")
                }
            }
        )
    }

    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.padding(vertical = 15.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(todaysDate(),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = ((backButtonPadding * 2) + backButtonSize).dp)
                )
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.padding (horizontal = backButtonPadding.dp)) {
                        TextButton(
                            onClick = { showPopup = true },
                            shape = CircleShape,
                            modifier = Modifier
                                .size(backButtonSize.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Retour icon"
                            )
                        }
                    }
                    Text("Retour", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
        }
        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                Modifier
                    .fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Page navigation")
            }
        }
    }
}
