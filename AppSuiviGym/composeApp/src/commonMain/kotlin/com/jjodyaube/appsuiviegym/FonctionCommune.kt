package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable

@Composable
expect fun getEntrainement(): Structure

@Composable
expect fun saveEntrainements(entrainements: Structure)

@Composable
expect fun jouerSonSuccess(): Unit

@Composable
expect fun jouerVibration(ms: Long = 1000L, amplitude: Int = 255)

