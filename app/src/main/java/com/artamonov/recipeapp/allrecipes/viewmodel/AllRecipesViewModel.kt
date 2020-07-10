package com.artamonov.recipeapp.allrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.artamonov.recipeapp.allrecipes.database.Recipe
import com.artamonov.recipeapp.allrecipes.database.RecipeRepository
import com.artamonov.recipeapp.allrecipes.database.RecipeRoomDatabase

    class AllRecipesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: RecipeRepository
    var allRecipes: LiveData<List<Recipe>>

        init {
        val wordsDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDao()
        repository = RecipeRepository(wordsDao)
        allRecipes = repository.allRecipes
        }

        fun updateList() {
            allRecipes = repository.allRecipes
        }

}