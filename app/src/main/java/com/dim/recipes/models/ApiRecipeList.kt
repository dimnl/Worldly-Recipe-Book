package com.dim.recipes.models

import com.google.gson.annotations.SerializedName

class ApiRecipeList(
    @SerializedName("meals") // SerializedName is from JSON structure API Request
    var apiRecipes: ArrayList<ApiRecipe>
) {
    fun toRecipeList(): RecipeList {
        val recipeList = RecipeList()

        for (apiRecipe in apiRecipes) {
            recipeList.add(
                Recipe(
                    apiRecipe.strMeal,
                    apiRecipe.strCategory,
                    apiRecipe.strArea,
                    apiRecipe.strInstructions,
                    apiRecipe.strMealThumb
                )
            )
        }

        return recipeList
    }
}

