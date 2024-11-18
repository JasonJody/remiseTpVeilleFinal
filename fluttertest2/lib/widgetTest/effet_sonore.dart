import 'package:flutter/material.dart';
import 'package:just_audio/just_audio.dart';

class EffetSonore extends StatelessWidget {
  const EffetSonore({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: SizedBox(
        width: 150,
        height: 60,
        child: FloatingActionButton(
          onPressed: () => jouerSon(),
          backgroundColor: Colors.black,
          foregroundColor: Colors.white,
          shape: const LinearBorder(),
          child: const FittedBox(
            child: Text(
              "Jouer le son",
              style: TextStyle(),
            ),
          ),
        ),
      ),
    );
  }

  void jouerSon() async {
    final player = AudioPlayer();
    await player.setAsset('assets/succees.mp3');
    player.play();
  }
}
