package com.example.prmanager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ExerciseItems)

    @Delete
    suspend fun delete(item: ExerciseItems)

    @Query("SELECT * FROM exercise_items")
    fun getAllExerciseItems(): LiveData<List<ExerciseItems>>
}