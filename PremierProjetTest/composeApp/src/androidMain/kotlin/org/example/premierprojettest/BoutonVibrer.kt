package org.example.premierprojettest.org.example.premierprojettest

import android.content.Context
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun BoutonVibrer() {

    val context1 = LocalContext.current
    val vibrator = context1.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

    val vibrateMs = 1000L
    val amplitude = 255

    Button(onClick = { vibrator.vibrate(
        CombinedVibration.createParallel(VibrationEffect.createOneShot(vibrateMs, amplitude))
    ) },
        contentPadding = PaddingValues(all = 20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text("Jouer la vibration")
    }
}
