package com.artamonov.recipeapp.newrecipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.artamonov.recipeapp.allrecipes.viewmodel.RecipeState
import com.artamonov.recipeapp.utils.default

sealed class NewRecipeState {
    object DefaultState : NewRecipeState()
    class RecipeIsAdded<User>(val user: User) : NewRecipeState()
    object NoRecipesState : NewRecipeState()
    object ErrorState : NewRecipeState()
}

class NewRecipeViewModel: ViewModel() {

    val state = MutableLiveData<NewRecipeState>().default(initialValue = NewRecipeState.DefaultState)



}