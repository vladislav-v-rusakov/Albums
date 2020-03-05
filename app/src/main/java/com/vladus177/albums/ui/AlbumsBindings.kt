package com.vladus177.albums.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vladus177.albums.ui.adapter.UsersListAdapter
import com.vladus177.albums.ui.model.UserView

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<UserView>) {
    //(listView.adapter as UsersListAdapter).submitList(items)
}

/*
@BindingAdapter(value = ["imageLoadUrl", "imageLoadSpinner"], requireAll = true)
@JvmStatic
fun loadImageWithSpinner(image: ImageView, imageUrl: String?, loading: View) {
    loading.visibility = View.VISIBLE
    imageUrl.let {
        Picasso.get()
            .load(imageUrl)
            .error(R.drawable.ic_person_place_holder)
            .into(image, object : Callback {

                override fun onSuccess() {
                    loading.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    loading.visibility = View.GONE
                }
            })
    }
}*/
