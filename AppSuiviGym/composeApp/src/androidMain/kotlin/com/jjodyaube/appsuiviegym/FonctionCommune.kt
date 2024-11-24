package com.jjodyaube.appsuiviegym

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.VibratorManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getEntrainement(): Structure = getFichier()
@Composable
actual fun saveEntrainements(entrainements: Structure) {saveFichierEntrainements(entrainements)}
@Composable
actual fun jouerSonSuccess() {
    val context = LocalContext.current
    AudioPlayer.jouerEffetSonore(context, R.raw.success)
}
@Composable
actual fun jouerVibration(ms: Long, amplitude: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
        Log.d("Vibration", "Vibration non supportée sur cette version d'API.")
        return
    }

    val context1 = LocalContext.current
    val vibratorManager = context1.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

    val vibrationEffect = VibrationEffect.createOneShot(ms, amplitude)
    vibratorManager.vibrate(CombinedVibration.createParallel(vibrationEffect))
}