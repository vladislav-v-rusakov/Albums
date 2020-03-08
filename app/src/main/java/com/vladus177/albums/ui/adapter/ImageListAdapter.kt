package com.vladus177.albums.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vladus177.albums.ui.model.ImageView
import com.squareup.picasso.Picasso
import com.vladus177.albums.R
import com.vladus177.albums.databinding.ViewImageItemBinding
import java.lang.Exception
import javax.inject.Inject


class ImageListAdapter @Inject constructor(private val picasso: Picasso) :
    ListAdapter<ImageView, ImageListAdapter.ViewHolder>(ImageDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, picasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageView, picasso: Picasso?) {

            picasso?.load(item.url)?.into(binding.ivPhoto, object : com.squareup.picasso.Callback {
                override fun onError(e: Exception?) {
                    binding.ivPhoto.setImageDrawable(binding.ivPhoto.context.getDrawable(R.drawable.ic_close_24dp))
                }

                override fun onSuccess() {
                    binding.ivPhoto.visibility = View.VISIBLE
                    binding.pbImageLoading.visibility = View.GONE
                }
            })
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewImageItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ImageDiffCallback : DiffUtil.ItemCallback<ImageView>() {
    override fun areItemsTheSame(oldItem: ImageView, newItem: ImageView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageView, newItem: ImageView): Boolean {
        return oldItem == newItem
    }
}