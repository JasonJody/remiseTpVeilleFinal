package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable

@Composable
expect fun getEntrainement(): Structure

@Composable
expect fun saveEntrainements(entrainements: Structure)