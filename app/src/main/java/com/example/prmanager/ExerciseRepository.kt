package com.example.prmanager

class ExerciseRepository(private val db:ExerciseDatabase) {
    suspend fun insert(items: ExerciseItems) = db.getExerciseDAO().insert(items)
    suspend fun delete(items: ExerciseItems) = db.getExerciseDAO().delete(items)

    fun getAllItems() = db.getExerciseDAO().getAllExerciseItems()
}