package com.dim.recipes.models.recipe

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    var id: String,
    var name: String,
    var category: String,
    var area: String,
    var instructions: String,
    var image: String
) : Parcelable {
    override fun toString(): String {
        return "Recipe(name='$name', category='$category', area='$area', image='$image')"
    }
}