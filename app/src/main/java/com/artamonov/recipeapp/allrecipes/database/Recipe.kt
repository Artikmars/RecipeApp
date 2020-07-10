package com.artamonov.recipeapp.allrecipes.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.StringReader

@Entity(tableName = "recipe_table")
data class Recipe(@PrimaryKey(autoGenerate = true) val id: Int, val title: String,
                  val description: String? = null, val imagePaths: List<String>? = mutableListOf()) {

}
