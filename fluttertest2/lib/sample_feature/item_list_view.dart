import 'package:flutter/material.dart';
import 'package:fluttertest2/sample_feature/item_details_view.dart';
import 'package:fluttertest2/widgetTest/bouton_flottant.dart';
import 'package:fluttertest2/widgetTest/changement_couleur_temp_reel.dart';
import 'package:fluttertest2/widgetTest/cree_un_fichier.dart';
import 'package:fluttertest2/widgetTest/effet_sonore.dart';
import 'package:fluttertest2/widgetTest/fenetre_popup.dart';
import 'package:fluttertest2/widgetTest/inputs.dart';
import 'package:fluttertest2/widgetTest/liste_rectangle.dart';
import 'package:fluttertest2/widgetTest/modifier_un_fichier.dart';
import 'package:fluttertest2/widgetTest/roue_couleur.dart';
import 'package:fluttertest2/widgetTest/scroll.dart';
import 'package:fluttertest2/widgetTest/titre_personnalise.dart';
import 'package:fluttertest2/widgetTest/vibration_test.dart';
import '../widgetTest/page_navigation.dart';

import 'test.dart';

/// Displays a list of SampleItems.
class ItemListView extends StatelessWidget {
  const ItemListView({
    super.key,
    this.tests = const [
      Test("Page navigation", PageNavigation()),
      Test("Bouton flottant", BoutonFlottant()),
      Test("Roue de couleur", RoueCouleur()),
      Test("Inputs", Inputs()),
      Test(
          "Des animations en temps réel (changement d’arrière-plan de couleur)",
          ChangementCouleurTempReel()),
      Test("Titre personnalisé", TitrePersonnalise()),
      Test("Scroll", Scroll()),
      Test("Fenêtre popup", FenetrePopup()),
      Test("Effet sonore", EffetSonore()),
      Test("Vibration", VibrationTest()),
      Test("Liste de rectangle", ListeRectangle()),
      Test("Créer un fichier", CreeUnFichier()),
      Test("Modifier/lire un fichier", ModifierUnFichier()),
    ],
  });

  static const routeName = '/';

  final List<Test> tests;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        itemCount: tests.length,
        itemBuilder: (BuildContext context, int index) {
          final test = tests[index];

          return ListTile(
              title: Text(test.title),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => ItemDetailsView(itemDetails: test)),
                );
              });
        },
      ),
    );
  }
}
