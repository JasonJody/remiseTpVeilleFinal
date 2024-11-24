package com.jjodyaube.appsuiviegym

import androidx.compose.ui.graphics.Color

private val poidsDunePlaque: HashMap<UniteDeMesure, Int> = hashMapOf(UniteDeMesure.KG to 20, UniteDeMesure.LBS to 45)

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

    fun getExerciceTitres(): List<String> {
        return listExercicesTitre
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
        return exercices.size
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

    fun removeExercice(exercice: Exercice) {
        exercices.remove(exercice)
    }
}

class Exercice(private var nom: String, private var nombreDeSet: Int) {
    private var sets: MutableList<HashMap<Int, WorkoutSet>>

    private fun getEmptySets(): HashMap<Int, WorkoutSet> {
        val map = HashMap<Int, WorkoutSet>();
        for (i in 1..nombreDeSet) {
            map[i] = WorkoutSet()
        }
        return map
    }

    init {
        sets = MutableList(1) { getEmptySets() }
    }

    fun addNewSets() {
        if (!anySetDone()) return

        val cloneOfLastSet = getNewSetWithOldValue()
        sets.add(cloneOfLastSet)
    }

    private fun getNewSetWithOldValue(): HashMap<Int, WorkoutSet> {
        val newSets = HashMap<Int, WorkoutSet>()
        for ((index, set) in sets.last()) {
            newSets[index] = WorkoutSet(
                set.getPoids(),
                set.getNombreRepetition(),
                set.getTypeEquipement(),
                set.getUniteDeMesure()
            )
        }
        return newSets
    }

    private fun anySetDone(): Boolean {
        for ((index, set) in sets.last()) {
            if (set.isSkipped() || set.isDone()) {
                return true
            }
        }
        return false
    }

    fun getNom(): String {
        return nom
    }

    fun getNombreSet(): Int {
        return nombreDeSet
    }

    fun isDone():Boolean {
        for ((index, set) in sets.last()) {
            if (!set.isDone()) {
                return false
            }
        }
        return true
    }

    fun getCurrentSets(): HashMap<Int, WorkoutSet> {
        return sets.last()
    }
}

class WorkoutSet {
    private val minValueToAddKg: Float = 1.25f
    private val minValueToAddLbs: Float = 2.5f
    private var poids: Float = 0f
    private var nombreDeRepetition: Int = 0
    private var typeEquipement: TypeEquipement = TypeEquipement.PLATES
    private var uniteDeMesure: UniteDeMesure = UniteDeMesure.KG
    private var isDone = false
    private var isSkipped = false

    constructor()

    constructor(poids: Float, nombreDeRepetition: Int, typeEquipement: TypeEquipement, uniteDeMesure: UniteDeMesure) {
        this.poids = poids
        this.nombreDeRepetition = nombreDeRepetition
        this.typeEquipement = typeEquipement
        this.uniteDeMesure = uniteDeMesure
    }

    fun isDone(): Boolean {
        return isDone
    }

    fun setIsDone(value: Boolean) {
        isDone = value
    }

    fun getNombreRepetition(): Int {
        return nombreDeRepetition
    }

    fun getTypeEquipement(): TypeEquipement {
        return typeEquipement
    }

    fun getUniteDeMesure(): UniteDeMesure {
        return uniteDeMesure
    }

    fun getPoids(): Float {
        return poids
    }

    fun getNombrePlaqueEtKgSupplementaire(): Array<Float> {
        val poidsPlaque = poidsDunePlaque[uniteDeMesure]!!

        val nombrePlaque = (poids / poidsPlaque).toInt()

        val poidsDesPlaques = nombrePlaque * poidsPlaque
        val poidsSupplementaire = poids - poidsDesPlaques

        return arrayOf(nombrePlaque.toFloat(), poidsSupplementaire)
    }

    fun addRep(repToAdd: Int) {
        if (repToAdd < 0 && nombreDeRepetition <= 0) return
        nombreDeRepetition += repToAdd
    }

    fun switchTypeEquipement() {
        typeEquipement =
            if (typeEquipement == TypeEquipement.PLATES)
                TypeEquipement.DUMBBELLS
            else
                TypeEquipement.PLATES
    }

    fun switchUniteDeMesure() {
            if (uniteDeMesure == UniteDeMesure.KG) {
                uniteDeMesure = UniteDeMesure.LBS
                poids = convertirEnLbs()
            }
            else {
                uniteDeMesure = UniteDeMesure.KG
                poids = convertirEnKg()
            }
    }

    private fun convertirEnKg(): Float {
        val plaquesEnLbs = listOf(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)
        val plaquesEnKg = listOf(20.0, 15.0, 10.0, 5.0, 2.5, 1.25)

        var poidsEnKg = 0.0
        var poidsRestant = poids.toDouble()

        for (i in plaquesEnLbs.indices) {
            val nbPlaques = (poidsRestant / plaquesEnLbs[i]).toInt()
            poidsEnKg += nbPlaques * plaquesEnKg[i]
            poidsRestant -= nbPlaques * plaquesEnLbs[i]
        }

        return poidsEnKg.toFloat()
    }

    private fun convertirEnLbs(): Float {
        val plaquesEnKg = listOf(20.0, 15.0, 10.0, 5.0, 2.5, 1.25)
        val plaquesEnLbs = listOf(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)

        var poidsEnLbs = 0.0
        var poidsRestant = poids.toDouble()

        for (i in plaquesEnKg.indices) {
            val nbPlaques = (poidsRestant / plaquesEnKg[i]).toInt()
            poidsEnLbs += nbPlaques * plaquesEnLbs[i]
            poidsRestant -= nbPlaques * plaquesEnKg[i]
        }

        return poidsEnLbs.toFloat()
    }

    fun skip() {
        isSkipped = true
    }

    fun isSkipped(): Boolean {
        return isSkipped
    }

    fun addPoids() {
        if (uniteDeMesure == UniteDeMesure.KG) {
            poids += minValueToAddKg
        }
        else {
            poids += minValueToAddLbs
        }
    }

    fun reducePoids() {
        if (poids <= 0) return
        if (uniteDeMesure == UniteDeMesure.KG) {
            poids -= minValueToAddKg
        }
        else {
            poids -= minValueToAddLbs
        }
    }

    fun setPoids(poids: Float) {
        this.poids = poids
    }

    fun setNombreRepetition(nombreDeRepetition: Int) {
        this.nombreDeRepetition = nombreDeRepetition
    }

    fun setTypeEquipement(typeEquipement: TypeEquipement) {
        this.typeEquipement = typeEquipement
    }

    fun setUniteDeMesure(uniteDeMesure: UniteDeMesure) {
        this.uniteDeMesure = uniteDeMesure
    }

    fun setIsSkipped(isSkipped: Boolean) {
        this.isSkipped = isSkipped
    }
}