package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import androidx.compose.foundation.layout.Arrangement as alignment1

@Composable
fun PageRoueDeCouleur() {
    val controller = rememberColorPickerController()

    Column(
        Modifier
            .fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = alignment1.Center
    ) {
        HsvColorPicker(
            modifier = Modifier
                .width(300.dp)
                .height(450.dp)
                .padding(10.dp),
            controller = controller
        )
    }
}
