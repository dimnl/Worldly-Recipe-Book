package com.dim.recipes.models.api

/**
 * Data class that represents a category from MealDB.
 */
data class ApiCategory(
    var idCategory: String,
    var strCategory: String,
    var strCategoryThumb: String,
    var strCategoryDescription: String
)