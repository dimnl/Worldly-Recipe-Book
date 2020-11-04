package com.dim.recipes.data

import com.dim.recipes.models.ApiRecipe
import com.dim.recipes.models.ApiRecipeList
import retrofit2.http.GET

interface ApiRequestRandomRecipe {
    @GET("random.php")
    suspend fun getRandomRecipe(): ApiRecipeList
}