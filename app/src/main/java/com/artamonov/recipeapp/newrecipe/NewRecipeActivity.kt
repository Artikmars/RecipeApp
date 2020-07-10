package com.artamonov.recipeapp.newrecipe

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.recipeapp.R
import com.artamonov.recipeapp.newrecipe.adapter.ImagesListAdapter
import com.artamonov.recipeapp.newrecipe.viewmodel.NewRecipeViewModel
import com.artamonov.recipeapp.utils.PostTextChangeWatcher
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_new_recipe.*

class NewRecipeActivity : AppCompatActivity() {

    lateinit var newRecipeViewModel: NewRecipeViewModel
    private lateinit var adapter: ImagesListAdapter
    private var newImage: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recipe)
        newRecipeViewModel = ViewModelProviders.of(this).get(NewRecipeViewModel::class.java)

        newRecipeViewModel.imagesMutableList.observe(this, Observer { images ->
            images?.let { adapter.setImages(it) }
        })

        new_recipe_save.setOnClickListener {
            newRecipeViewModel.saveNewRecipe()
            finish()
        }

        new_title_edit.addTextChangedListener(PostTextChangeWatcher {
            newRecipeViewModel.titleChanged(it) })

        new_description_edit.addTextChangedListener(PostTextChangeWatcher {
            newRecipeViewModel.descriptionChanged(it) })

        initAdapter()

    }

    private fun initAdapter() {
        adapter = ImagesListAdapter(this, object : ImagesListAdapter.OnItemClickListener {
            override fun onRemoveImageItemClick(position: Int) {
                newRecipeViewModel.removeImage(position)
            }

            override fun onAddNewImage() {
                dispatchTakePictureIntent()
            }
        })
        minor_images_list.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.HORIZONTAL
        minor_images_list.layoutManager = linearLayoutManager
    }

    private fun dispatchTakePictureIntent() {
        ImagePicker.with(this)
            .cropSquare()
            .compress(1024)
            .maxResultSize(300, 300)
            .galleryOnly()
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                newImage = data?.data
                newRecipeViewModel.addNewImage(newImage)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
        }
    }

}