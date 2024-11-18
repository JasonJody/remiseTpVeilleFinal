import 'package:flutter/material.dart';

class ListeRectangle extends StatelessWidget {
  const ListeRectangle({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.all(8.0),
      itemCount: 10,
      itemBuilder: (context, index) {
        return Padding(
          padding: const EdgeInsets.all(5),
          child: TextButton(
            onPressed: () => print("Bouton $index à été appuyé"),
            style: ButtonStyle(
                padding: WidgetStateProperty.all<EdgeInsets>(
                    const EdgeInsets.all(15)),
                foregroundColor:
                    const WidgetStatePropertyAll<Color>(Colors.black),
                shape: WidgetStateProperty.all<RoundedRectangleBorder>(
                    RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(5),
                        side: const BorderSide(color: Colors.black)))),
            child: Padding(
              padding: const EdgeInsets.all(20),
              child: Row(
                children: [
                  Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Titre $index',
                        style: const TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        'Lundi, mardi, mercredi',
                        style: TextStyle(
                          fontSize: 14,
                          color: Colors.grey.shade700,
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
