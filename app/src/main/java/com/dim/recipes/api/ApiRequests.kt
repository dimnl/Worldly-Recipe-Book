package com.dim.recipes.api

import com.dim.recipes.models.api.ApiCategoryList
import com.dim.recipes.models.api.ApiRecipeList
import retrofit2.http.GET

interface ApiRequests {
    @GET("random.php")
    suspend fun getRandomRecipe(): ApiRecipeList

    @GET("categories.php")
    suspend fun getCategories(): ApiCategoryList
}