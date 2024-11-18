import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';
import 'dart:io';

class ModifierUnFichier extends StatefulWidget {
  const ModifierUnFichier({super.key});

  @override
  State<ModifierUnFichier> createState() => ModifierUnFichierState();
}

class ModifierUnFichierState extends State<ModifierUnFichier> {
  final _formKey = GlobalKey<FormState>();
  final _textController = TextEditingController();
  String _fileContent = '';

  @override
  void initState() {
    super.initState();
    _loadFileContent();
  }

  Future<void> _loadFileContent() async {
    try {
      final directory = await getApplicationDocumentsDirectory();
      final file = File('${directory.path}/fichierCree.txt');

      if (await file.exists()) {
        String content = await file.readAsString();
        setState(() {
          _fileContent = content;
        });
      } else {
        Navigator.pop(context);
      }
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Erreur lors de la lecture du fichier.')),
      );
    }
  }

  Future<void> _appendToFile(String content) async {
    try {
      final directory = await getApplicationDocumentsDirectory();
      final file = File('${directory.path}/fichierCree.txt');

      await file.writeAsString('$content\n', mode: FileMode.append);

      _loadFileContent();

      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Contenu ajouté au fichier existant.')),
      );
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Erreur lors de l\'écriture du fichier.')),
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
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Contenu du fichier :\n$_fileContent',
                style: const TextStyle(fontSize: 16),
              ),
              const SizedBox(height: 20),
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
                    "Texte à ajouter au fichier",
                    style: TextStyle(color: Colors.black),
                  ),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Ce champ ne peut pas être vide!';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 20),
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
                        _appendToFile(content);
                        _textController.clear();
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
