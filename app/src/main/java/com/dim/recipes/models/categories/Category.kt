package com.dim.recipes.models.categories

data class Category (
    var id: String,
    var name: String,
    var image: String,
    var description: String
){
    override fun toString(): String {
        return "Category(id='$id', name='$name', image='$image')"
    }
}