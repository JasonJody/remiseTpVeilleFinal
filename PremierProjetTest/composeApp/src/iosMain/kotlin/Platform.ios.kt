package org.example.premierprojettest

import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
actual fun getBoutonJouerSon(): @Composable () -> Unit = { Text("Aucun son") }
actual fun getBoutonJouerVibration(): @Composable () -> Unit = { Text("Aucune vibration") }
actual fun getBoutonCreeFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit): @Composable () -> Unit = { Text("Création de fichier non disponible sur iOS") }
actual fun getBoutonModifierFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit, setToUpdate: () -> Unit): @Composable () -> Unit = { Text("Pas de bouton pour modifier le fichier") }
actual fun getTextLectureFichier(toUpdate: Boolean): @Composable () -> Unit = { Text("Pas de truc à lire pour le fichier") }