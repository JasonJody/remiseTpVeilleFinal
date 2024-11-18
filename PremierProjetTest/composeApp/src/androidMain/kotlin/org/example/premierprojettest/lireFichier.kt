package org.example.premierprojettest.org.example.premierprojettest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileOutputStream


@Composable
fun LireFichier(toUpdate: Boolean) {

    LaunchedEffect(toUpdate) {}

    val context = LocalContext.current

    fun lireFichier(): String {
        val file = File(context.filesDir, "fichierCree.txt")
        return if (file.exists()) {
            file.readText()
        } else {
            "Vide"
        }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Text("Contenu du fichier:")
        Text(lireFichier())
    }
}
