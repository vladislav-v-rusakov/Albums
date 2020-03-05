package com.vladus177.albums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vladus177.albums.databinding.ViewAlbumItemBinding
import com.vladus177.albums.ui.model.AlbumView

class AlbumListAdapter(private val itemClickListener: OnAlbumItemClickListener) :
    ListAdapter<AlbumView, AlbumListAdapter.ViewHolder>(AlbumDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewAlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AlbumView, clickListener: OnAlbumItemClickListener) {
            binding.album = item
            binding.llAlbumItem.setOnClickListener { clickListener.onItemClicked(item.id) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewAlbumItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

interface OnAlbumItemClickListener {

    fun onItemClicked(albumId: Long?)

}

class AlbumDiffCallback : DiffUtil.ItemCallback<AlbumView>() {
    override fun areItemsTheSame(oldItem: AlbumView, newItem: AlbumView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumView, newItem: AlbumView): Boolean {
        return oldItem == newItem
    }
}