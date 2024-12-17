package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.composants.AppBar

@Composable
fun CreditPage(
    navController: NavController
) {

    Page(
        appBar = AppBar(navController)
            .titre("Crédit")
            .backButton(true)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 30.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Créé et imaginé par le grand Roberto")
            Box(modifier = Modifier.weight(1f))
            Text("© 2024 RobyKout. All Rights Reserved.")
        }
    }
}