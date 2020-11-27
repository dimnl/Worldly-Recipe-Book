package com.dim.recipes.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dim.recipes.R

fun loadImageIntoImageView(httpImage:String, imageView:ImageView, context:Context){
    val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_internet_recipe)
        .error(R.drawable.ic_internet_recipe)

    Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
        .load(httpImage)
        .into(imageView)
}