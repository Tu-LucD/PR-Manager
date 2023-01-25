package com.example.prmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_items")
data class ExerciseItems (
    @ColumnInfo(name = "itemName")
    var itemName: String,

    @ColumnInfo(name = "nbReps")
    var numberReps: Int,

    @ColumnInfo(name = "nbPounds")
    var nbPounds: Float
){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}