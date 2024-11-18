import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

class CreeUnFichier extends StatefulWidget {
  const CreeUnFichier({super.key});

  @override
  State<CreeUnFichier> createState() => _CreeUnFichierState();
}

class _CreeUnFichierState extends State<CreeUnFichier> {
  final _formKey = GlobalKey<FormState>();
  final _textController = TextEditingController();

  Future<void> _createFile(String content) async {
    try {
      final directory = await getApplicationDocumentsDirectory();
      final file = File('${directory.path}/fichierCree.txt');
      await file.writeAsString("$content\n");

      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Fichier créé avec succès!')),
      );
      _textController.clear();
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Erreur lors de la création du fichier.')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(30),
      child: Center(
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                controller: _textController,
                autofocus: true,
                cursorColor: Colors.black,
                decoration: const InputDecoration(
                  enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.black)),
                  focusedBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.black)),
                  border: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.black)),
                  label: Text(
                    "À mettre dans le fichier",
                    style: TextStyle(color: Colors.black),
                  ),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Euhh ceci ne peut pas être vide!';
                  }
                  return null;
                },
              ),
              Expanded(
                child: Align(
                  alignment: Alignment.bottomCenter,
                  child: ElevatedButton(
                    style: ElevatedButton.styleFrom(
                        backgroundColor: Colors.black,
                        foregroundColor: Colors.white),
                    onPressed: () {
                      if (_formKey.currentState!.validate()) {
                        final content = _textController.text;
                        _createFile(content);
                      }
                    },
                    child: const Text('Envoyer'),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
