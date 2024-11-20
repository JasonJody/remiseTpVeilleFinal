package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable

@Composable
actual fun getHashMapEntrainement(): Structure = getFichier()
@Composable
actual fun saveEntrainements(entrainements: Structure) {saveFichierEntrainements(entrainements)}
