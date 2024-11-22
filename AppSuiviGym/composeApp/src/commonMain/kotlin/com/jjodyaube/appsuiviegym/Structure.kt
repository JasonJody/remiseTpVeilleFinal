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

    fun removeSousWorkout(sousWorkout: SousWorkout) {
        sousWorkout.delete()
    }

    fun addSousWorkout(workout: Workout, sousWorkout: SousWorkout) {
        workout.addSousWorkout(sousWorkout)
    }

}

class Workout(
    private var journees: MutableSet<Jours>,
    private var couleur: Color,
    private var titre: String,
    private var isDeleted: Boolean = false,
    private var sousWorkouts: MutableList<SousWorkout> = mutableListOf()
) {

    fun getSousWorkout(): List<SousWorkout> {
        // Besoin parce que app crash, je pense que gson met liste vide a null
        // a la place de retourner une list vide, c'est ma théorie.
        if (sousWorkouts == null) return mutableListOf()
        return sousWorkouts.filter { !it.isDeleted() }
    }

    fun getTitre() = titre
    fun getCouleur() = couleur
    fun getJournees() = journees

    fun delete() {
        isDeleted = true
    }

    fun isDeleted(): Boolean {
        return isDeleted
    }

    fun getTotalSousWorkout(): MutableList<SousWorkout> = sousWorkouts
    fun getSousWorkoutAt(index: Int): SousWorkout = sousWorkouts.elementAt(index)

    fun getIndexOfSousWorkout(sousWorkout: SousWorkout): Int {
        return sousWorkouts.indexOf(sousWorkout)
    }

    fun addSousWorkout(sousWorkout: SousWorkout) {
        if (sousWorkouts == null) {
            sousWorkouts = mutableListOf(sousWorkout)
        }
        sousWorkouts.add(sousWorkout)
    }
}

class SousWorkout(
    private var titre: String,
    private var couleur: Color,
) {
    private var isDeleted: Boolean = false
    private var exercices: MutableList<Int> = mutableListOf()

    fun getTitre() = titre
    fun getCouleur() = couleur

    fun delete() {
        isDeleted = true
    }

    fun isDeleted(): Boolean {
        return isDeleted
    }

    fun getNombreEntrainement(): Int {
        return (0..10).random()
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