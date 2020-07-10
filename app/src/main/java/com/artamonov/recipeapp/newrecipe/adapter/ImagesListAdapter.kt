package com.artamonov.recipeapp.newrecipe.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artamonov.recipeapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.new_recipe_image_item.view.*

class ImagesListAdapter internal constructor(
    private val context: Context,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ImagesListAdapter.ImageViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var images = emptyList<Uri>()

    companion object {
        const val ADD_IMAGE = 0
        const val DEFAULT_TYPE = 1
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(path: Uri) {
            itemView.remove_image.visibility = View.VISIBLE
            Glide
                .with(context)
                .load(path)
                .dontAnimate()
                .placeholder(R.drawable.ic_baseline_insert_photo_24)
                .into(itemView.image_item)
            itemView.remove_image.setOnClickListener {
                listener.onRemoveImageItemClick(adapterPosition)
            }
        }

        fun bindAddNewItem() {
            itemView.remove_image.visibility = View.GONE
            Glide
                .with(context)
                .load(R.drawable.ic_baseline_add_to_photos_24)
                .placeholder(R.drawable.ic_baseline_add_to_photos_24)
                .dontAnimate()
                .into(itemView.image_item)

            itemView.image_item.setOnClickListener {
                listener.onAddNewImage()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
                ADD_IMAGE
            } else {
               DEFAULT_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = inflater.inflate(R.layout.new_recipe_image_item, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (holder.itemViewType == ADD_IMAGE) {
            holder.bindAddNewItem()
        } else {
            val imagePath = images[position]
            holder.bindItem(imagePath)
        }
    }

    internal fun setImages(images: List<Uri>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun getItemCount() = images.size

    interface OnItemClickListener {
        fun onRemoveImageItemClick(position: Int)
        fun onAddNewImage()
    }
}