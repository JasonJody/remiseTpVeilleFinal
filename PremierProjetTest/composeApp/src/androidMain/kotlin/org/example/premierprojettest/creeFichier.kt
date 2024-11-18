package org.example.premierprojettest.org.example.premierprojettest

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File
import java.io.FileOutputStream


@Composable
fun BoutonCreeFichier(text: String, setHasError: (Boolean) -> Unit, setText: () -> Unit) {
    val context = LocalContext.current

    fun saveFichier() {
        val file = File(context.filesDir, "fichierCree.txt")

        FileOutputStream(file).use { outputStream ->
            outputStream.write(text.toByteArray())
        }
    }

    fun envoyerValeurs() {
        if (text.isEmpty()) {
            setHasError(true)
            return
        }

        saveFichier()
        setText()
    }


    Button(
        onClick = { envoyerValeurs() },
        modifier = Modifier
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(all = 15.dp)
    ) {
        Text("Envoyer")
    }
}
