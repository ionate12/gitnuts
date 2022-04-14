package com.example.gitnuts.utils

import android.content.res.ColorStateList
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gitnuts.R


@BindingAdapter("tintBackground")
fun tintBackground(view: View, @ColorRes colorRes: Int) {
    view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, colorRes))
}

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, uri: Uri?) {
    Glide.with(imageView.context)
        .load(uri)
        .apply(RequestOptions().override(120,120))
        .placeholder(R.drawable.ic_baseline_account_circle_24)
        .into(imageView)
}