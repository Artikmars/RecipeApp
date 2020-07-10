package com.artamonov.recipeapp.allrecipes.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(@PrimaryKey(autoGenerate = true) val id: Int? = null, var title: String? = null,
                  var description: String? = null, var imagePaths: MutableList<String>? = mutableListOf())
