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

    fun getWorkouts() = workouts.filter { !it.isDeleted() }

    fun addWorkout(workout: Workout) {
        workouts.add(workout)
    }

    fun removeWorkout(workout: Workout) {
        workout.delete()
    }

    override fun toString(): String {
        return "${workouts.size}"
    }

    fun getWorkoutsAt(index: Int): Workout {
        return workouts.elementAt(index)
    }

    fun getIndexOfWorkout(workout: Workout): Int {
        return workouts.indexOf(workout)
    }

    fun getTotalWorkouts(): MutableList<Workout> = workouts

}

class Workout(
    private var journees: MutableSet<Jours>,
    private var couleur: Color,
    private var titre: String,
    private var isDeleted: Boolean = false
) {
    private var sousWorkout: MutableList<SousWorkout> = mutableListOf()

    fun getSousWorkout() = sousWorkout.filter { !it.isDeleted() }

    fun getTitre() = titre
    fun getCouleur() = couleur
    fun getJournees() = journees

    fun delete() {
        isDeleted = true
    }

    fun isDeleted(): Boolean {
        return isDeleted
    }

    fun getTotalSousWorkout(): MutableList<SousWorkout> = sousWorkout
    fun getSousWorkoutAt(index: Int): SousWorkout = sousWorkout.elementAt(index)
}

class SousWorkout(
    private var titre: String,
    private var couleur: Color,
    private var isDeleted: Boolean = false
) {
    private var exercices: MutableList<Int> = mutableListOf()

    fun getTitre() = titre
    fun getCouleur() = couleur

    fun delete() {
        isDeleted = true
    }

    fun isDeleted(): Boolean {
        return isDeleted
    }
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