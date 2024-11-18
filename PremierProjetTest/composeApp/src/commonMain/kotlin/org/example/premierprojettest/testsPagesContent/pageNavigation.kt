package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement as alignment1

@Composable
fun PageNavigation() {
    Column(
        Modifier
            .fillMaxWidth().fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = alignment1.Center
    ) {
        Text("Page navigation")
    }
}
