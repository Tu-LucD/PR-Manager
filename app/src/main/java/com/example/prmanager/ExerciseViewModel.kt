package com.example.prmanager

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExerciseViewModel(private val repository: ExerciseRepository):ViewModel() {

    fun insert(items:ExerciseItems) = GlobalScope.launch {
        repository.insert(items)
    }

    fun delete(items:ExerciseItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllExerciseItems() = repository.getAllItems()
}