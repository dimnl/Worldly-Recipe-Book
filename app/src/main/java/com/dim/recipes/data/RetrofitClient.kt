package com.dim.recipes.data

import com.dim.recipes.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        val retrofit =  Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiRequestRandomRecipe::class.java)
    }
}