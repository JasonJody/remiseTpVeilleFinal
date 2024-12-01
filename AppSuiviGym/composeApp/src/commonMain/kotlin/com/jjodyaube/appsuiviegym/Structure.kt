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
    private var listExercicesTitre: MutableSet<String> = mutableSetOf()
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

    fun getExerciceTitres(): Set<String> {
        return listExercicesTitre
    }

    fun deleteExeciceTitre(titreExercice: String) {
        listExercicesTitre.remove(titreExercice)
    }

    fun moveUpWorkout(workout: Workout) {
        val index = workouts.indexOf(workout)
        if (index > 0) {
            val temp = workouts[index]
            workouts[index] = workouts[index - 1]
            workouts[index - 1] = temp
        }
    }

    fun moveDownWorkout(workout: Workout) {
        val index = workouts.indexOf(workout)
        if (index >= 0 && index < workouts.size - 1) {
            val temp = workouts[index]
            workouts[index] = workouts[index + 1]
            workouts[index + 1] = temp
        }
    }

}

class Workout(
    private var journees: MutableSet<Jours>,
    private var couleur: Color,
    private var titre: String,
    private var sousWorkouts: MutableList<SousWorkout> = mutableListOf()
) {

    fun getSousWorkout(): MutableList<SousWorkout> {
        return sousWorkouts
    }

    fun getTitre() = titre
    fun getCouleur() = couleur
    fun getJournees() = journees

    fun getIndexOfSousWorkout(sousWorkout: SousWorkout): Int {
        return sousWorkouts.indexOf(sousWorkout)
    }

    fun addSousWorkout(sousWorkout: SousWorkout) {
        sousWorkouts.add(sousWorkout)
    }

    fun removeSousWorkout(sousWorkout: SousWorkout) {
        sousWorkouts.remove(sousWorkout)
    }

    fun getSousWorkoutAt(indexSousWorkout: Int): SousWorkout {
        return sousWorkouts[indexSousWorkout]
    }

    fun moveUpSousWorkout(sousWorkout: SousWorkout) {
        val index = sousWorkouts.indexOf(sousWorkout)
        if (index > 0) {
            val temp = sousWorkouts[index]
            sousWorkouts[index] = sousWorkouts[index - 1]
            sousWorkouts[index - 1] = temp
        }
    }

    fun moveDownSousWorkout(sousWorkout: SousWorkout) {
        val index = sousWorkouts.indexOf(sousWorkout)
        if (index >= 0 && index < sousWorkouts.size - 1) {
            val temp = sousWorkouts[index]
            sousWorkouts[index] = sousWorkouts[index + 1]
            sousWorkouts[index + 1] = temp
        }
    }

    fun setJournees(journees: MutableSet<Jours>) {
        this.journees = journees
    }

    fun setTitre(titre: String) {
        this.titre = titre
    }

    fun setCouleurActive(couleur: Color) {
        this.couleur = couleur
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

    fun getExercices(): MutableList<Exercice> {
        return exercices
    }

    fun removeExercice(exercice: Exercice) {
        exercices.remove(exercice)
    }

    fun getIndexOfExercice(exercice: Exercice): Int {
        return exercices.indexOf(exercice)
    }

    fun getExerciceAt(index: Int): Exercice {
        return exercices[index]
    }

    fun moveUpExercice(exercice: Exercice) {
        val index = exercices.indexOf(exercice)
        if (index > 0) {
            val temp = exercices[index]
            exercices[index] = exercices[index - 1]
            exercices[index - 1] = temp
        }
    }

    fun moveDownExercice(exercice: Exercice) {
        val index = exercices.indexOf(exercice)
        if (index >= 0 && index < exercices.size - 1) {
            val temp = exercices[index]
            exercices[index] = exercices[index + 1]
            exercices[index + 1] = temp
        }
    }

    fun setTitre(titre: String) {
        this.titre = titre
    }

    fun setCouleur(couleur: Color) {
        this.couleur = couleur
    }
}

class Exercice(private var nom: String,
               private var nombreDeSet: Int,
               private var minRep: Int? = null,
               private var maxRep: Int? = null
) {
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

    fun hasRep(): Boolean {
        return minRep != null
    }

    fun getMinRep(): Int? {
        return minRep
    }

    fun getMaxRep(): Int? {
        return maxRep
    }


    fun addNewSets() {
        if (!anySetDone()) return

        val cloneOfLastSet = getNewSetWithOldValue()
        sets.add(cloneOfLastSet)
    }

    private fun getNewSetWithOldValue(): HashMap<Int, WorkoutSet> {
        val newSets = HashMap<Int, WorkoutSet>()
        for ((index, set) in sets.last()) {
            if (index > nombreDeSet) {
                break
            }
            newSets[index] = WorkoutSet(
                set.getPoids(),
                set.getNombreRepetition(),
                set.getTypeEquipement(),
                set.getUniteDeMesure()
            )
        }
        if (newSets.size < nombreDeSet) {
            for (i in newSets.size..nombreDeSet) {
                newSets[i] = WorkoutSet()
            }
        }
        return newSets
    }

    private fun anySetDone(): Boolean {
        for ((_, set) in sets.last()) {
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
        for ((_, set) in sets.last()) {
            if (!set.isDone() && !set.isSkipped()) {
                return false
            }
        }
        return true
    }

    fun getCurrentSets(): HashMap<Int, WorkoutSet> {
        return sets.last()
    }

    fun getAllSets(): List<HashMap<Int, WorkoutSet>> {
        return sets
    }

    fun setNombreDeSet(nombreDeSet: Int) {
        if (this.nombreDeSet < nombreDeSet) {
            for (i in this.nombreDeSet..nombreDeSet) {
                sets.last()[i] = WorkoutSet()
            }
        } else if (this.nombreDeSet > nombreDeSet) {
            for (i in this.nombreDeSet downTo (nombreDeSet + 1)) {
                sets.last().remove(i)
            }
        }
        this.nombreDeSet = nombreDeSet
    }

    fun setNom(nom: String) {
        this.nom = nom
    }

    fun setMinRep(minRep: Int?) {
        this.minRep = minRep
    }

    fun setMaxRep(maxRep: Int?) {
        this.maxRep = maxRep
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
}