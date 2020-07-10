package com.artamonov.recipeapp.allrecipes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {

    @Query("SELECT * from recipe_table ORDER BY id ASC")
    fun getRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(recipe: Recipe)

    @Query("DELETE FROM recipe_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}