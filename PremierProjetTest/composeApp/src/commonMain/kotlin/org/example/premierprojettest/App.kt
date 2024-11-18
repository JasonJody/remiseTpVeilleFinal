package org.example.premierprojettest

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.premierprojettest.org.example.premierprojettest.testsPagesContent.PageBoutonVibration
import org.example.premierprojettest.org.example.premierprojettest.testsPagesContent.PageCreeFichier
import org.example.premierprojettest.org.example.premierprojettest.testsPagesContent.PageLireModifierFichier
import org.example.premierprojettest.org.example.premierprojettest.testsPagesContent.PageListRectangle
import org.example.premierprojettest.testsPagesContent.PageBoutonAnimation
import org.example.premierprojettest.testsPagesContent.PageBoutonEffetSonore
import org.example.premierprojettest.testsPagesContent.PageBoutonFlottant
import org.example.premierprojettest.testsPagesContent.PageInputs
import org.example.premierprojettest.testsPagesContent.PageNavigation
import org.example.premierprojettest.testsPagesContent.PagePopup
import org.example.premierprojettest.testsPagesContent.PageRoueDeCouleur
import org.example.premierprojettest.testsPagesContent.PageScroll
import org.example.premierprojettest.testsPagesContent.PageTestContainer
import org.example.premierprojettest.testsPagesContent.PageTitrePerso
import org.jetbrains.compose.ui.tooling.preview.Preview

fun tests(navController: NavController): Array<Test> {
    return arrayOf(
        Test("Page navigation", "pageNav", { PageNavigation() }),
        Test("Titre personnalisé", "titrePerso", { PageTitrePerso() }),
        Test("Bouton flottant", "btnFlot", { PageBoutonFlottant() }),
        Test("Roue de couleur", "roueCouleur", { PageRoueDeCouleur() }),
        Test("Inputs", "inputs", { PageInputs() }),
        Test(
            "Des animations en temps réel (changement d’arrière-plan de couleur)",
            "btnAnimAvecRoueCouleur",
            { PageBoutonAnimation() }),
        Test("Scroll", "scroll", { PageScroll() }),
        Test("Fenêtre popup", "popup", { PagePopup(navController) }),
        Test("Effet sonore", "soundEffect", { PageBoutonEffetSonore() }),
        Test("Vibration", "vibration", { PageBoutonVibration() }),
        Test("Liste de rectangle", "listeRectangles", { PageListRectangle() }),
        Test("Créer un fichier", "creeFichier", { PageCreeFichier() }),
        Test("Modifier/lire un fichier", "modifFichier", { PageLireModifierFichier() })
    )

}
@Composable
@Preview
fun App() {
    MaterialTheme {

        val navController = rememberNavController()

        NavHost(
            navController,
            startDestination = "home",
            enterTransition = { EnterTransition.None},
            exitTransition = { ExitTransition.None},
            popEnterTransition = {EnterTransition.None},
            popExitTransition = {ExitTransition.None},
        ) {
            composable("home") {
                HomePage(navController)
            }

            for (test in tests(navController)) {
                composable(test.route, ) {
                    PageTestContainer(navController, test.titre, test.page)
                }
            }
        }
    }
}

@Composable
fun HomePage(navController: NavController) {
    Box(Modifier.padding(10.dp)) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ) {
            for (test in tests(navController)) {
                TextButton(
                    onClick = { navController.navigate(test.route) },
                    modifier = Modifier.fillMaxWidth().heightIn(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    shape = androidx.compose.ui.graphics.RectangleShape
                ) {
                    Text(
                        test.titre,
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}