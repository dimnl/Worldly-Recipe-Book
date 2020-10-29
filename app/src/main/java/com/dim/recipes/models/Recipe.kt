package com.dim.recipes.models

data class Recipe(
    var name: String,
    var category: String,
    var area: String,
    var instructions: String,
    var image: String
) {
    override fun toString(): String {
        return "Recipe(name='$name', category='$category', area='$area', image='$image')"
    }
}