import 'package:flutter/material.dart';
import 'package:fluttertest2/sample_feature/test.dart';

class ItemDetailsView extends StatelessWidget {
  final Test itemDetails;

  const ItemDetailsView({super.key, required this.itemDetails});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(itemDetails.title),
        ),
        body: itemDetails.widget);
  }
}
