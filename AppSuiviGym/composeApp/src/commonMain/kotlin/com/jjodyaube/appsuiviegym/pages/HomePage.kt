package com.jjodyaube.appsuiviegym.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Structure
import com.jjodyaube.appsuiviegym.composants.Action
import com.jjodyaube.appsuiviegym.composants.AppBar
import com.jjodyaube.appsuiviegym.composants.ButtonActions
import com.jjodyaube.appsuiviegym.composants.listingWorkouts.ListWorkouts
import compose.icons.FeatherIcons
import compose.icons.feathericons.Check
import compose.icons.feathericons.Plus
import compose.icons.feathericons.Settings

@Composable
fun HomePage(
    navController: NavController,
    entrainement: Structure,
) {

    var isUpdating by remember { mutableStateOf(false) }

    Page(
        appBar = AppBar(navController)
            .titre("Workouts")
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    ListWorkouts(navController, entrainement, isUpdating)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(align = Alignment.BottomEnd)
            ) {
                if (!isUpdating) {
                    ButtonActions()
                        .action(Action(
                            icon = FeatherIcons.Settings,
                            action = { isUpdating = !isUpdating },
                            description = "Activer la mise à jour"
                        ))
                        .action(Action(
                            icon = FeatherIcons.Plus,
                            action = { navController.navigate("creer/workout/-1") },
                            description = "Ajouter workout"
                        ))
                        .build()
                } else {
                    ButtonActions()
                        .action(Action(
                            icon = FeatherIcons.Check,
                            action = { isUpdating = !isUpdating },
                            description = "Désactiver la mise à jour"
                        ))
                        .build()
                }
            }
        }
    }
}