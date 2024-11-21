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
    private var exercices: MutableList<Exercice> = mutableListOf()
    private var workouts: MutableList<Workout> = mutableListOf()

    fun getExercices() = exercices
    fun getWorkouts() = workouts

    fun addWorkout(workout: Workout) {
        workouts.add(workout)
    }

    fun removeWorkout(workout: Workout) {
        workouts.remove(workout)
    }

    override fun toString(): String {
        return "${workouts.size}"
    }

}

class Workout(
    private var journees: MutableSet<Jours>,
    private var couleur: Color,
    private var titre: String
) {
    private var sousWorkout: MutableList<SousWorkout> = mutableListOf()


    fun getTitre() = titre
    fun getCouleur() = couleur
    fun getJournees() = journees
}

class SousWorkout(private var titre: String, private var couleur: Color) {
    private var exercices: MutableList<Int> = mutableListOf()

    fun getTitre() = titre
    fun getCouleur() = couleur
}

class Exercice(private var id: Int, private var nom: String, private var nombreDeSet: Int) {
    private var sets: MutableList<Set> = MutableList(nombreDeSet) { Set() }
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