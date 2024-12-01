package com.jjodyaube.appsuiviegym

class GlobalVariable private constructor(
    private var _currentWorkoutIndex: Int? = null,
    private var _currentSousWorkoutIndex: Int? = null,
    private var _currentExercice: Int? = null,
    private var _creeExerciceRechercheTitreChoisi: String? = null,
) {
    companion object {

        private var instance: GlobalVariable? = null

        fun getInstance(): GlobalVariable {
            if (instance == null) {
                instance = GlobalVariable()
            }

            return instance as GlobalVariable
        }
    }

    fun getCurrentWorkout(): Int? = _currentWorkoutIndex
    fun setCurrentWorkout(workoutIndex: Int) {
        _currentWorkoutIndex = workoutIndex
    }

    fun getCurrentSousWorkout(): Int? = _currentSousWorkoutIndex
    fun setCurrentSousWorkout(workoutIndex: Int) {
        _currentSousWorkoutIndex = workoutIndex
    }

    fun getCurrentExercice(): Int? = _currentExercice
    fun setCurrentExercice(exerciceIndex: Int) {
        _currentExercice = exerciceIndex
    }

    fun getcreeExerciceRechercheTitreChoisi(): String? = _creeExerciceRechercheTitreChoisi
    fun setcreeExerciceRechercheTitreChoisi(newTitre: String?) {
        _creeExerciceRechercheTitreChoisi = newTitre
    }
}