import 'package:flutter/material.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';

class RoueCouleur extends StatefulWidget {
  const RoueCouleur({super.key});

  @override
  State<RoueCouleur> createState() => _RoueCouleurState();
}

class _RoueCouleurState extends State<RoueCouleur> {
  Color currentColor = Colors.white;

  void changeColor(Color color) => setState(() => currentColor = color);

  @override
  Widget build(BuildContext context) {
    return ClipRect(
      child: Align(
        alignment: Alignment.topCenter,
        heightFactor: 0.23,
        child: ColorPicker(
          pickerColor: currentColor,
          onColorChanged: changeColor,
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
