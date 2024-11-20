package com.jjodyaube.appsuiviegym

enum class TypeEquipement {
    DUMBBELLS, PLATES
}

enum class UniteDeMesure {
    KG, LBS
}

class Structure() {
    private var exercices: List<Exercice>
    private var workouts: List<Workout>

    init {
        exercices = emptyList()
        workouts = emptyList()
    }

    fun getExercices() = exercices
    fun getWorkouts() = workouts

}

class Workout(private var titre: String) {
    private var sousWorkout: List<SousWorkout> = emptyList()
}

class SousWorkout(private var titre: String) {
    private var sousWorkout: List<SousWorkout> = emptyList()
}

class Exercice(private var nom: String, private var nombreDeSet: Int) {
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