package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FloatingBtnAdd(navController: NavController, navUrl: String) {
    FloatingActionButton(
        onClick = { navController.navigate(navUrl) },
        contentColor = Color.White,
        backgroundColor = Color.Black,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Bouton ajouter")
    }
}