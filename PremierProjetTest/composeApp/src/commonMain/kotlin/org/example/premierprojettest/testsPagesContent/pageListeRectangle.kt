package org.example.premierprojettest.org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement as alignment1

@Composable
fun PageListRectangle() {
    Column(
        Modifier
            .fillMaxWidth().
            fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
    ) {
        for(item in 0..10) {
            Box(modifier = Modifier.padding(10.dp)) {
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(5.dp))
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Élément $item", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("Lundi, mardi, mercredi")
                    }
                }
            }
        }
    }
}
