package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable

@Composable
expect fun getHashMapEntrainement(): Structure

@Composable
expect fun saveEntrainements(entrainements: Structure)
