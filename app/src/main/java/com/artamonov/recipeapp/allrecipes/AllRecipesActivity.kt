package com.artamonov.recipeapp.allrecipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.artamonov.recipeapp.R
import com.artamonov.recipeapp.allrecipes.adapter.RecipeListAdapter
import com.artamonov.recipeapp.allrecipes.viewmodel.AllRecipesViewModel
import kotlinx.android.synthetic.main.activity_main.*


class AllRecipesActivity : AppCompatActivity() {

    private lateinit var allRecipesViewModel: AllRecipesViewModel
    private lateinit var adapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        allRecipesViewModel = ViewModelProviders.of(this).get(AllRecipesViewModel::class.java)

        allRecipesViewModel.allRecipes.observe(this, Observer { recipes ->
            recipes?.let { adapter.setWords(it) }
        })

    }

    private fun initAdapter() {
        adapter = RecipeListAdapter(this)
        recipe_recycler_view.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        recipe_recycler_view.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(recipe_recycler_view.context,
            linearLayoutManager.orientation
        )
        recipe_recycler_view.addItemDecoration(dividerItemDecoration)
    }
}