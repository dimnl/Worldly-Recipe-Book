package com.dim.recipes.models.api

import com.dim.recipes.models.categories.Category
import com.dim.recipes.models.categories.CategoryList
import com.google.gson.annotations.SerializedName

class ApiCategoryList(
    @SerializedName("categories") // SerializedName is from JSON structure API Request
    var apiCategories: ArrayList<ApiCategory>
) {
    fun toCategoryList(): CategoryList {
        val categoryList = CategoryList()

        for (apiCategory in apiCategories) {
            categoryList.add(
                Category(
                    apiCategory.idCategory,
                    apiCategory.strCategory,
                    apiCategory.strCategoryThumb,
                    apiCategory.strCategoryDescription
                )
            )
        }

        return categoryList
    }
}

