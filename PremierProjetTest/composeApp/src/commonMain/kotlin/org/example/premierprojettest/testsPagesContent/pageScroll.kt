package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement as alignment1

@Composable
fun PageScroll() {
    Column(
        Modifier
            .fillMaxWidth().
            fillMaxHeight()
            .verticalScroll(rememberScrollState()),
    ) {
        for(item in 0..10) {
            Box(modifier = Modifier.height(150.dp)) {
                Text("Élément $item")
            }
        }
    }
}
