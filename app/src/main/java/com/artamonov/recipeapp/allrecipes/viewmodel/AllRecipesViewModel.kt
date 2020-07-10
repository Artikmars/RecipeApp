package com.artamonov.recipeapp.allrecipes.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.artamonov.recipeapp.allrecipes.database.Recipe
import com.artamonov.recipeapp.allrecipes.database.RecipeRepository
import com.artamonov.recipeapp.allrecipes.database.RecipeRoomDatabase
import com.artamonov.recipeapp.utils.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class RecipeState {
    object DefaultState : RecipeState()
    class RecipeIsAdded<User>(val user: User) : RecipeState()
    object NoRecipesState : RecipeState()
    object ErrorState : RecipeState()
}

    class AllRecipesViewModel(application: Application): AndroidViewModel(application) {

    val state = MutableLiveData<RecipeState>().default(initialValue = RecipeState.DefaultState)

    private val repository: RecipeRepository
    val allRecipes: LiveData<List<Recipe>>

    init {
        val wordsDao = RecipeRoomDatabase.getDatabase(application, viewModelScope).recipeDao()
        repository = RecipeRepository(wordsDao)
        allRecipes = repository.allRecipes
    }

    fun insert(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(recipe)
    }

    fun delete(recipe: Recipe) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteRecipe(recipe)
    }


}