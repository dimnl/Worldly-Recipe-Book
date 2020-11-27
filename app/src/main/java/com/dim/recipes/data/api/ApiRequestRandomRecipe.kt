package com.dim.recipes.data.api

import com.dim.recipes.models.api.ApiRecipeList
import retrofit2.http.GET

interface ApiRequestRandomRecipe {
    @GET("random.php")
    suspend fun getRandomRecipe(): ApiRecipeList
}