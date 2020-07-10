package com.artamonov.recipeapp.allrecipes.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.recipeapp.R
import com.artamonov.recipeapp.allrecipes.database.Recipe
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.all_recipes_list_item.view.*

class RecipeListAdapter internal constructor(
    private val context: Context
) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var recipes = emptyList<Recipe>()

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(recipe: Recipe) {
            itemView.recipe_title.text = recipe.title
            itemView.recipe_description.text = recipe.description

            if (!recipe.imagePaths.isNullOrEmpty()) {
                recipe.imagePaths?.get(0)?.let {
                    Glide.with(context)
                        .load(Uri.parse(recipe.imagePaths?.get(0)))
                        .dontAnimate()
                        .placeholder(R.drawable.ic_baseline_insert_photo_24)
                        .into(itemView.all_recipes_first_image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = inflater.inflate(R.layout.all_recipes_list_item, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bindItem(recipe)
    }

    internal fun setRecipes(words: List<Recipe>) {
        this.recipes = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = recipes.size
}