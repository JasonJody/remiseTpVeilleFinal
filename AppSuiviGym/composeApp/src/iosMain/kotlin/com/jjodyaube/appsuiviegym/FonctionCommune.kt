package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable

@Composable
actual fun getEntrainement(): Structure = Structure()
@Composable
actual fun saveEntrainements(entrainements: Structure) {}
@Composable
actual fun jouerSonSuccess(): Unit {}
@Composable
actual fun jouerVibration(ms: Long, amplitude: Int) {}
