package com.vladus177.albums.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.vladus177.albums.R
import com.vladus177.albums.databinding.ViewDynamicInfoBinding

class DynamicInformation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: ViewDynamicInfoBinding? = null

    init {
        binding = ViewDynamicInfoBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    fun showLoading() {
        this.visibility = View.VISIBLE
        binding?.apply {
            isLoading = true
            executePendingBindings()
        }
    }

    fun hideLoading() {
        this.visibility = View.GONE
        binding?.apply {
            isLoading = true
            executePendingBindings()
        }
    }

    fun showError() {
        this.visibility = View.VISIBLE
        binding?.apply {


            executePendingBindings()
        }
    }

    fun showConnectionError() {
        this.visibility = View.VISIBLE
        binding?.apply {
            tvTitle.text = resources.getString(R.string.connection_error_title)
            tvMessage.text = resources.getString(R.string.connection_error_message)
            executePendingBindings()
        }
    }

    fun showEmptyList() {
        this.visibility = View.VISIBLE
        binding?.apply {


            executePendingBindings()
        }
    }

    fun setActionButtonClickListener(clickListener: () -> Unit) {
        binding?.btnAction?.setOnClickListener {
            clickListener()
        }
    }

}