package com.navi.assignment.ui

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.navi.assignment.ui.customviews.CircleImageView

class CustomBindingAdapter {

    companion object{

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImageUrl(imageView: CircleImageView?, imageUrl: String?) {

            imageView?.context?.let {
                Glide.with(it)
                    .load(imageUrl)
                    .into(imageView)
            }
        }
    }
}