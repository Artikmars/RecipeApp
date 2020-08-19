package com.artamonov.recipeapp.newrecipe.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import com.artamonov.recipeapp.allrecipes.database.Recipe
import com.artamonov.recipeapp.allrecipes.database.RecipeRepository
import com.artamonov.recipeapp.allrecipes.database.RecipeRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewRecipeViewModel(application: Application): AndroidViewModel(application) {

    private val repository: RecipeRepository
    private var newRecipe: Recipe? = null
    val imagesMutableList: MutableLiveData<MutableList<Uri>> = MutableLiveData()
    private val images: MutableList<Uri> = mutableListOf()

    init {
        val wordsDao = RecipeRoomDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(wordsDao)
        images.add(Uri.EMPTY)
        imagesMutableList.postValue(images)
    }

    fun saveNewRecipe() = viewModelScope.launch(Dispatchers.IO) {
        newRecipe?.let { repository.insert(newRecipe!!) }
    }

    fun titleChanged(newText: String?) {
        if (newRecipe == null && newText != null) {
            newRecipe = Recipe(title = newText.trim())
        } else {
            newText?.let { newRecipe?.title = newText.trim() }
        }
    }

    fun descriptionChanged(newText: String?) {
        if (newRecipe == null && newText != null) {
            newRecipe = Recipe(description = newText.trim())
        } else {
            newText?.let { newRecipe?.description = newText.trim() }
        }
    }

    fun removeImage(position: Int) {
        images.removeAt(position)
        imagesMutableList.postValue(images)
    }

    fun addNewImage(newImage: Uri?) {
        if (newRecipe == null && newImage != null) {
            newRecipe = Recipe(imagePaths = mutableListOf())
        } else {
            newImage?.let {
                images.add(newImage)
                imagesMutableList.postValue(images)
                newRecipe?.imagePaths?.add(it.toString())
            }        }

    }

}