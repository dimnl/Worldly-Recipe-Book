package com.dim.recipes.api

import com.dim.recipes.models.api.ApiCategoryList
import com.dim.recipes.models.api.ApiRecipeList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {
    @GET("random.php")
    suspend fun getRandomRecipe(): ApiRecipeList

    @GET("categories.php")
    suspend fun getCategories(): ApiCategoryList

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): ApiRecipeList

    @GET("search.php")
    suspend fun searchRecipe(@Query("s") name: String): ApiRecipeList

    @GET("lookup.php")
    suspend fun lookupRecipe(@Query("i") id: String): ApiRecipeList
}