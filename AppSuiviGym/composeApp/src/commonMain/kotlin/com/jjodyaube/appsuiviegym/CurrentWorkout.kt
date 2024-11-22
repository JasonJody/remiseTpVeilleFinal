package com.jjodyaube.appsuiviegym

class CurrentWorkout private constructor(
    private var _currentWorkoutIndex: Int? = null,
    private var _currentSousWorkoutIndex: Int? = null
) {
    companion object {

        private var instance: CurrentWorkout? = null

        fun getInstance(): CurrentWorkout {
            if (instance == null) {
                instance = CurrentWorkout()
            }

            return instance as CurrentWorkout
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
}