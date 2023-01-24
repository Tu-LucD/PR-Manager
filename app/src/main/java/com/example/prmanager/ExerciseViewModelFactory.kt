package com.example.prmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExerciseViewModelFactory (private val repository: ExerciseRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ExerciseViewModel(repository) as T
    }
}