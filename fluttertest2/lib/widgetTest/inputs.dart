import 'package:flutter/material.dart';

class Inputs extends StatefulWidget {
  const Inputs({super.key});

  @override
  State<Inputs> createState() => _InputsState();
}

class _InputsState extends State<Inputs> {
  final _formKey = GlobalKey<FormState>();
  List<bool> checkboxes = [true, true, false];
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
                  "Input label",
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
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                for (int i = 0; i < checkboxes.length; i++)
                  Checkbox(
                    activeColor: Colors.black,
                    value: checkboxes[i],
                    onChanged: (bool? value) {
                      setState(() {
                        checkboxes[i] = value ?? false;
                      });
                    },
                  ),
              ],
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
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text('Processing Data')),
                      );
                    }
                  },
                  child: const Text('Envoyer'),
                ),
              ),
            ),
          ],
        ),
      )),
    );
  }
}
