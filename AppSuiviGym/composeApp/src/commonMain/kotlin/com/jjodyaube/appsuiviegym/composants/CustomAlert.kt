package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomAlertDialog(
    message: String,
    actionText: String,
    showAlert: MutableState<Boolean>,
    action: (() -> Unit)?,
) {

    Dialog(
        onDismissRequest = { showAlert.value = false },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    ) {
        Card(
            modifier = Modifier
                .padding(25.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    message,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Left
                )

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { showAlert.value = false },
                        modifier = Modifier
                            .background(
                                Color.Black,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .height(35.dp)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent
                        ),
                    ) {
                        Text(
                            text = "Annuler",
                            modifier = Modifier,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    OutlinedButton(
                        onClick = {
                            if (action != null) {
                                action()
                            }
                        },
                        modifier = Modifier
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .height(35.dp)
                            .weight(1f),
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White
                        ),
                    ) {
                        Text(
                            text = actionText,
                            modifier = Modifier,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}