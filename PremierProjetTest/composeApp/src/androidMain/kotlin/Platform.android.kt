package org.example.premierprojettest

import androidx.compose.runtime.Composable
import org.example.premierprojettest.org.example.premierprojettest.BoutonCreeFichier
import org.example.premierprojettest.org.example.premierprojettest.BoutonModifierFichier
import org.example.premierprojettest.org.example.premierprojettest.BoutonVibrer
import org.example.premierprojettest.org.example.premierprojettest.LireFichier

@Composable
actual fun getBoutonJouerSon(): @Composable () -> Unit = { BoutonEffetSonore() }
actual fun getBoutonJouerVibration(): @Composable () -> Unit = { BoutonVibrer() }
actual fun getBoutonCreeFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit): @Composable () -> Unit = { BoutonCreeFichier(text, setHasError, setText) }
actual fun getBoutonModifierFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit, setToUpdate: () -> Unit): @Composable () -> Unit = { BoutonModifierFichier(text, setHasError, setText, setToUpdate) }
actual fun getTextLectureFichier(toUpdate: Boolean): @Composable () -> Unit = { LireFichier(toUpdate) }