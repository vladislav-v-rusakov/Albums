package com.vladus177.albums.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vladus177.albums.databinding.ViewUserItemBinding
import com.vladus177.albums.ui.model.UserView

class UsersListAdapter(private val itemClickListener: OnItemClickListener) :
    ListAdapter<UserView, UsersListAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ViewUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserView, clickListener: OnItemClickListener) {
            binding.user = item
            binding.llItemView.setOnClickListener { clickListener.onItemClicked(item.id) }
            binding.ivFavorite.setOnClickListener{clickListener.onFavoriteClicked(item.id!!, !item.isFavorite) }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewUserItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

interface OnItemClickListener {

    fun onItemClicked(userId: Long?)

    fun onFavoriteClicked(userId: Long, favorite: Boolean)
}

class TaskDiffCallback : DiffUtil.ItemCallback<UserView>() {
    override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean {
        return oldItem == newItem
    }
}
