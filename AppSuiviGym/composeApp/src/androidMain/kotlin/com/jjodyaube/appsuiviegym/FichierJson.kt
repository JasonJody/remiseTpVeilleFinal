package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

@Composable
fun getFichier(): Structure {
    var fichierJson = Structure()

    fun saveFichier(file: File) {
        val fileOutputStream = FileOutputStream(file)
        val outputStreamWriter = OutputStreamWriter(fileOutputStream)
        val json = Gson().toJson(fichierJson)
        outputStreamWriter.write(json)
        outputStreamWriter.close()
    }

    val context = LocalContext.current
    val file = File(context.filesDir, "entrainements.json")

    if (file.exists()) {
        val fileInputStream = FileInputStream(file)
        val inputStreamReader = InputStreamReader(fileInputStream)
        fichierJson = Gson().fromJson(inputStreamReader, Structure::class.java)
        inputStreamReader.close()
    } else {
        fichierJson = Structure()
        saveFichier(file)
    }

    return fichierJson
}

@Composable
fun saveFichierEntrainements(entrainements: Structure) {
    val context = LocalContext.current
    val file = File(context.filesDir, "entrainements.json")

    val fileOutputStream = FileOutputStream(file)
    val outputStreamWriter = OutputStreamWriter(fileOutputStream)
    val json = Gson().toJson(entrainements)
    outputStreamWriter.write(json)
    outputStreamWriter.close()
}