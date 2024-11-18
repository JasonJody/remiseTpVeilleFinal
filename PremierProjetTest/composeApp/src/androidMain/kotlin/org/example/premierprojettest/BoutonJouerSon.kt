package org.example.premierprojettest

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.example.premierprojettest.AndroidAudioPlayer.jouerSon

@Composable
fun BoutonEffetSonore() {

    val context = LocalContext.current

    fun jouerSonWrapper() {
        jouerSon(context)
    }

    Button(onClick = { jouerSonWrapper() },
        contentPadding = PaddingValues(all = 20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text("Jouer le son")
    }
}
