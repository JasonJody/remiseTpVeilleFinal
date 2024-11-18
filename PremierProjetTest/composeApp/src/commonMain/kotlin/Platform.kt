package org.example.premierprojettest

import androidx.compose.runtime.Composable

@Composable
expect fun getBoutonJouerSon(): @Composable () -> Unit
expect fun getBoutonJouerVibration(): @Composable () -> Unit
expect fun getBoutonCreeFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit): @Composable () -> Unit
expect fun getBoutonModifierFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit, setToUpdate: () -> Unit): @Composable () -> Unit
expect fun getTextLectureFichier(toUpdate: Boolean): @Composable () -> Unit