package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jjodyaube.appsuiviegym.composants.AppBar

@Composable
fun Page(appBar: AppBar, content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        appBar.build()

        Column(
            Modifier
                .fillMaxSize(),
        ) {
            content()
        }
    }
}