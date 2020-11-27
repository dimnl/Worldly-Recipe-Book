package com.dim.recipes.data.api

import com.dim.recipes.models.api.ApiRecipeList
import retrofit2.http.GET

interface ApiRequestCategories {
    @GET("categories.php")
    suspend fun getCategories(): ApiRecipeList
}