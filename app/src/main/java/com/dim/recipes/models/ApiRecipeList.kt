package com.dim.recipes.models

import com.google.gson.annotations.SerializedName

class ApiRecipeList(
    @SerializedName("meals") // SerializedName is from JSON structure API Request
    var apiRecipes: ArrayList<ApiRecipe>
) {
    public fun toRecipeList(): ArrayList<Recipe> {
        var recipeList = ArrayList<Recipe>()

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

    fun add(apiRecipe: ApiRecipe) {
        apiRecipes.add(apiRecipe)
    }

    fun get(i: Int): ApiRecipe {
        return apiRecipes.get(i)
    }
}

