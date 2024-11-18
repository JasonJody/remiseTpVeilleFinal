import 'package:flutter/material.dart';
import 'package:vibration/vibration.dart';

class VibrationTest extends StatelessWidget {
  const VibrationTest({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: SizedBox(
        width: 150,
        height: 60,
        child: FloatingActionButton(
          onPressed: () => vibrer(),
          backgroundColor: Colors.black,
          foregroundColor: Colors.white,
          shape: const LinearBorder(),
          child: const FittedBox(
            child: Text(
              "Jouer la vibration",
              style: TextStyle(),
            ),
          ),
        ),
      ),
    );
  }

  void vibrer() async {
    if (await Vibration.hasVibrator() == true) {
      Vibration.vibrate();
    }
  }
}
