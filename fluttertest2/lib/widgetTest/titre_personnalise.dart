import 'package:flutter/material.dart';

class TitrePersonnalise extends StatelessWidget {
  const TitrePersonnalise({super.key});

  @override
  Widget build(BuildContext context) {
    List<String> mois = [
      'janvier',
      'février',
      'mars',
      'avril',
      'mai',
      'juin',
      'juillet',
      'août',
      'septembre',
      'octobre',
      'novembre',
      'décembre'
    ];

    String getDate() {
      var now = DateTime.now();
      return "${now.day} ${mois[now.month - 1]} ${now.year}";
    }

    return Column(
      children: [
        Row(
          children: [
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 68),
                  child: Text(
                    getDate(),
                    style: const TextStyle(
                      fontSize: 12,
                      color: Colors.black54,
                    ),
                  ),
                ),
                const SizedBox(height: 4),
                Row(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(left: 10, right: 10),
                      child: IconButton(
                          onPressed: () => {Navigator.pop(context)},
                          icon: const Icon(Icons.arrow_back)),
                    ),
                    const Text(
                      'Historique\nBench press',
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
