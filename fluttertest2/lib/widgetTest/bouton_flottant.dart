import 'package:flutter/material.dart';

class BoutonFlottant extends StatelessWidget {
  const BoutonFlottant({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(15),
      child: Center(
          child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          FloatingActionButton(
            shape: const CircleBorder(),
            onPressed: () => {},
            backgroundColor: Colors.black,
            foregroundColor: Colors.white,
            child: const Text(
              "+",
              style: TextStyle(fontSize: 30, fontWeight: FontWeight.w300),
            ),
          ),
        ],
      )),
    );
  }
}
