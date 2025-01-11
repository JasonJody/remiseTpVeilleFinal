package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import compose.icons.FeatherIcons
import compose.icons.feathericons.Check
import compose.icons.feathericons.Plus
import compose.icons.feathericons.Settings

@Composable
fun BoutonAjoutModifier(
    navController: NavController,
    hasSousWorkout: Boolean,
    isUpdating: MutableState<Boolean>,
    urlCree: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(align = Alignment.BottomEnd)
    ) {
        val actionButtonAdd = {
            Action(
                icon = FeatherIcons.Plus,
                action = { navController.navigate(urlCree) },
                description = "Ajouter workout"
            )
        }

        if (!hasSousWorkout) {
            ButtonActions()
                .action(
                    actionButtonAdd()
                )
                .build()
            return@Box
        }

        if (!isUpdating.value) {
            ButtonActions()
                .action(
                    Action(
                        icon = FeatherIcons.Settings,
                        action = { isUpdating.value = !isUpdating.value },
                        description = "Activer la mise à jour"
                    )
                )
                .action(
                    actionButtonAdd()
                )
                .build()
        } else {
            ButtonActions()
                .action(
                    Action(
                        icon = FeatherIcons.Check,
                        action = { isUpdating.value = !isUpdating.value },
                        description = "Désactiver la mise à jour"
                    )
                )
                .build()
        }
    }
}