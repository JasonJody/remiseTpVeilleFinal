import 'package:flutter/material.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';

class ChangementCouleurTempReel extends StatefulWidget {
  const ChangementCouleurTempReel({super.key});

  @override
  ChangementCouleurTempReelState createState() =>
      ChangementCouleurTempReelState();
}

class ChangementCouleurTempReelState extends State<ChangementCouleurTempReel> {
  Color currentColor = Colors.white;

  void changeColor(Color color) {
    setState(() => currentColor = color);
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        RoueCouleurAnimation(
          currentColor: currentColor,
          onColorChanged: changeColor,
        ),
        ElevatedButton(
          onPressed: () => {},
          style: ElevatedButton.styleFrom(
            backgroundColor: currentColor,
            foregroundColor: Colors.black,
          ),
          child: const Text("Bouton animé"),
        )
      ],
    );
  }
}

class RoueCouleurAnimation extends StatelessWidget {
  final Color currentColor;
  final ValueChanged<Color> onColorChanged;

  const RoueCouleurAnimation(
      {required this.currentColor, required this.onColorChanged, super.key});

  @override
  Widget build(BuildContext context) {
    return ClipRect(
      child: Align(
        alignment: Alignment.topCenter,
        heightFactor: 0.70,
        child: ColorPicker(
          pickerColor: currentColor,
          onColorChanged: onColorChanged,
          colorPickerWidth: 250,
          pickerAreaHeightPercent: 0.7,
          enableAlpha: false,
          labelTypes: const [],
          displayThumbColor: true,
          paletteType: PaletteType.hueWheel,
          hexInputBar: false,
        ),
      ),
    );
  }
}
