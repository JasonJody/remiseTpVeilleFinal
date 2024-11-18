import 'package:flutter/material.dart';

class FenetrePopup extends StatelessWidget {
  const FenetrePopup({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Row(
          children: [
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 4),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(left: 10, right: 10),
                      child: IconButton(
                          onPressed: () {
                            _dialogBuilder(context).then((val) {
                              if (val == true) {
                                Navigator.pop(context);
                              }
                            });
                          },
                          icon: const Icon(Icons.arrow_back)),
                    ),
                    const Text(
                      'Formulaire aléatoire',
                      style: TextStyle(
                        fontSize: 24,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ],
        ),
        const Expanded(
          child: Padding(
            padding: EdgeInsets.symmetric(horizontal: 16.0),
            child: Center(
              child: Text("Page"),
            ),
          ),
        ),
      ],
    );
  }
}

Future<bool?> _dialogBuilder(BuildContext context) {
  return showDialog<bool>(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: const Text('Sauvegarde perdu'),
        content: const Text(
          'Aucune donnée sera sauvegradé\n'
          'Êtes-vous sûr de vouloir quitter ?\n',
        ),
        actions: <Widget>[
          TextButton(
            style: TextButton.styleFrom(
              textStyle: Theme.of(context).textTheme.labelLarge,
            ),
            child: const Text('Rester'),
            onPressed: () {
              Navigator.of(context).pop(false);
            },
          ),
          TextButton(
            style: TextButton.styleFrom(
              textStyle: Theme.of(context).textTheme.labelLarge,
            ),
            child: const Text('Quitter'),
            onPressed: () {
              Navigator.of(context).pop(true);
            },
          ),
        ],
      );
    },
  );
}
