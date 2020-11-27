package com.dim.recipes.models.api

import com.google.gson.annotations.SerializedName

class ApiCategoryList(
    @SerializedName("categories") // SerializedName is from JSON structure API Request
    var apiCategories: ArrayList<ApiRecipe>,
)

