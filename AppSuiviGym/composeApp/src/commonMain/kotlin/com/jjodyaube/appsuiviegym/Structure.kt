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