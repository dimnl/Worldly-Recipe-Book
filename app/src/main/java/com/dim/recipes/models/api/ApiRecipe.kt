package com.dim.recipes.models.api

/**
 * Data class that represents a recipe from MealDB.
 *
 * Not all of the fields returned from the API are shown here; only the ones used in this project.
 */
data class ApiRecipe(
    var idMeal: String,
    var strMeal: String,
    var strCategory: String,
    var strArea: String,
    var strInstructions: String,
    var strMealThumb: String
)