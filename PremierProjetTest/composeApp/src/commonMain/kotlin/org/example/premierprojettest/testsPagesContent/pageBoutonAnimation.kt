package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import androidx.compose.foundation.layout.Arrangement as alignment1

@Composable
fun PageBoutonAnimation() {
    val controller = rememberColorPickerController()
    var couleurActive by remember { mutableStateOf(Color.Black) }

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
            controller = controller,
            onColorChanged = { colorEnvelope: ColorEnvelope ->
                couleurActive = colorEnvelope.color
            }
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = couleurActive
            )
        ) {
            Text("Bouton animé")
        }
    }
}
