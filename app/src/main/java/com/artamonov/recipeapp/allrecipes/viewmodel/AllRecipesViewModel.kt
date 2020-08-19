package com.artamonov.recipeapp.allrecipes.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.artamonov.recipeapp.allrecipes.database.Recipe
import com.artamonov.recipeapp.allrecipes.database.RecipeRepository

class AllRecipesViewModel @ViewModelInject constructor(
    private val repository: RecipeRepository
) : ViewModel(), LifecycleObserver {
    var allRecipes: LiveData<List<Recipe>>

    init {
        allRecipes = repository.allRecipes
    }

    fun updateList() {
        allRecipes = repository.allRecipes
    }

}