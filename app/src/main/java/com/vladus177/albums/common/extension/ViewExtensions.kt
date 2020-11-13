package com.vladus177.albums.common.extension

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.vladus177.albums.common.listener.SingleClickListener

fun View.setSingleOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SingleClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun ImageView.load(url: String) {
    Picasso.get().load(url).centerCrop().into(this)
}