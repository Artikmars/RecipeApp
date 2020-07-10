package com.artamonov.recipeapp.allrecipes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.recipeapp.R
import com.artamonov.recipeapp.allrecipes.database.Recipe
import kotlinx.android.synthetic.main.all_recipes_list_item.view.*

class RecipeListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecipeListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Recipe>()

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(recipe: Recipe) {
            itemView.recipe_title.text = recipe.title
            itemView.recipe_description.text = recipe.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.all_recipes_list_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val recipe = words[position]
        holder.bindItem(recipe)
    }

    internal fun setWords(words: List<Recipe>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}