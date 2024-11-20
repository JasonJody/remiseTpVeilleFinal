package com.jjodyaube.appsuiviegym

import androidx.compose.ui.graphics.Color

enum class TypeEquipement {
    DUMBBELLS, PLATES
}

enum class UniteDeMesure {
    KG, LBS
}
enum class Jours {
    LUNDI, MARDI, MERCREDI, JEUDI, VENDREDI, SAMEDI, DIMANCHE
}

class Structure {
    private var exercices: List<Exercice>
    private var workouts: List<Workout>

    init {
        exercices = emptyList()
        workouts = emptyList()
    }

    fun getExercices() = exercices
    fun getWorkouts() = workouts

}

class Workout(private var titre: String, private var couleur: Color) {
    private var sousWorkout: List<SousWorkout> = emptyList()
    private var journees: List<Jours> = emptyList()

    fun getTitre() = titre
    fun getCouleur() = couleur
    fun getJournees() = journees
}

class SousWorkout(private var titre: String, private var couleur: Color) {
    private var exercices: List<Int> = emptyList()

    fun getTitre() = titre
    fun getCouleur() = couleur
}

class Exercice(private var id: Int, private var nom: String, private var nombreDeSet: Int) {
    private var sets: List<Set> = List(nombreDeSet) { Set() }
}

class Set {
    private var poids: Int
    private var nombreDeRepetition: Int
    private var typeEquipement: TypeEquipement
    private var uniteDeMesure: UniteDeMesure

    init {
        this.poids = 0
        this.nombreDeRepetition = 0
        this.typeEquipement = TypeEquipement.PLATES
        this.uniteDeMesure = UniteDeMesure.KG
    }
}