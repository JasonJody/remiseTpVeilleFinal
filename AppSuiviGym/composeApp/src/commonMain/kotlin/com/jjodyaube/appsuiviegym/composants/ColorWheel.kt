package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker

@Composable
fun RoueDeCouleur(controller: ColorPickerController, onColorChanged: (ColorEnvelope) -> Unit) {
    HsvColorPicker(
        modifier = Modifier
            .width(300.dp)
            .height(300.dp)
            .padding(10.dp),
        controller = controller,
        onColorChanged = onColorChanged
    )
}
