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
    private var listExercicesTitre: MutableList<String> = mutableListOf()
    private var workouts: MutableList<Workout> = mutableListOf()

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

    fun getWorkoutsAt(index: Int): Workout {
        return workouts.elementAt(index)
    }

    fun getIndexOfWorkout(workout: Workout): Int {
        return workouts.indexOf(workout)
    }

    fun addSousWorkout(workout: Workout, sousWorkout: SousWorkout) {
        workout.addSousWorkout(sousWorkout)
    }

    fun addExercice(titreExercice: String) {
        listExercicesTitre.add(titreExercice)
    }

}

class Workout(
    private var journees: MutableSet<Jours>,
    private var couleur: Color,
    private var titre: String,
    private var sousWorkouts: MutableList<SousWorkout> = mutableListOf()
) {

    fun getSousWorkout(): List<SousWorkout> {
        return sousWorkouts
    }

    fun getTitre() = titre
    fun getCouleur() = couleur
    fun getJournees() = journees

    fun getIndexOfSousWorkout(sousWorkout: SousWorkout): Int {
        return sousWorkouts.indexOf(sousWorkout)
    }

    fun addSousWorkout(sousWorkout: SousWorkout) {
        if (sousWorkouts == null) {
            sousWorkouts = mutableListOf(sousWorkout)
        }
        sousWorkouts.add(sousWorkout)
    }

    fun removeSousWorkout(sousWorkout: SousWorkout) {
        sousWorkouts.remove(sousWorkout)
    }

    fun getSousWorkoutAt(indexSousWorkout: Int): SousWorkout {
        return sousWorkouts[indexSousWorkout]
    }
}

class SousWorkout(
    private var titre: String,
    private var couleur: Color,
) {
    private var exercices: MutableList<Exercice> = mutableListOf()

    fun getTitre() = titre
    fun getCouleur() = couleur

    fun getNombreEntrainement(): Int {
        return (0..10).random()
    }

    fun addExercice(exercice: Exercice) {
        exercices.add(exercice)
    }

    fun done() {
        for (exercice in exercices) {
            exercice.addNewSets()
        }
    }

    fun getExercices(): List<Exercice> {
        return exercices
    }
}

class Exercice(private var nom: String, private var nombreDeSet: Int) {
    private var sets: MutableList<HashMap<Int, Set>>
    private var isDone: Boolean = false

    private fun getEmptySets(): HashMap<Int, Set> {
        val map = HashMap<Int, Set>();
        for (i in 1..nombreDeSet) {
            map[i] = Set()
        }
        return map
    }

    init {
        sets = MutableList(1) { getEmptySets() }
    }

    fun addNewSets() {
        sets.add(getEmptySets())
    }

    fun getNom(): String {
        return nom
    }

    fun getNombreSet(): Int {
        return nombreDeSet
    }

    fun isDone():Boolean {
        return isDone
    }
}

class Set {
    private var poids: Int = 0
    private var nombreDeRepetition: Int = 0
    private var typeEquipement: TypeEquipement = TypeEquipement.PLATES
    private var uniteDeMesure: UniteDeMesure = UniteDeMesure.KG
    private var isDone = false
}