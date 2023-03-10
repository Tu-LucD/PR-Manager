package com.example.prmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExerciseItems::class],version = 1)
abstract class ExerciseDatabase:RoomDatabase() {

    abstract fun getExerciseDAO(): ExerciseDAO

    companion object{
        @Volatile
        private var instance: ExerciseDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{
                instance = it
            }
        }


        private fun createDatabase(context: Context) =
                Room.databaseBuilder(
                context.applicationContext,
                ExerciseDatabase::class.java,
                "Exercise.db"
            ).build()
    }
}