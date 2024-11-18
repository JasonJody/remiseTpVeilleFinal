import 'package:flutter/material.dart';

class Scroll extends StatelessWidget {
  const Scroll({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [
          for (int i = 0; i < 10; i++)
            SizedBox(
              width: 300,
              height: 100,
              child: Text("Élément $i"),
            )
        ],
      ),
    );
  }
}
