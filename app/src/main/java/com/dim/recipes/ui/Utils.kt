package com.dim.recipes.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dim.recipes.R

fun loadImageIntoImageView(httpImage: String, imageView: ImageView, context: Context) {
    val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_internet_recipe)
        .error(R.drawable.ic_internet_recipe)

    Glide.with(context)
        .applyDefaultRequestOptions(requestOptions)
        .load(httpImage)
        .into(imageView)
}

class TopMarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = margin
    }
}