package com.dim.recipes.data

import com.dim.recipes.api.ApiRequests
import com.dim.recipes.api.RetrofitClient
import com.dim.recipes.models.categories.CategoryList
import com.dim.recipes.models.recipe.RecipeList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeRepository {
    companion object {
        private var client = RetrofitClient.create()
        private var clientRequest = client.create(ApiRequests::class.java)
        var recipeList = RecipeList()
        var categoryList = CategoryList()

        private val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }

        fun retrieveCategories() {
            CoroutineScope(Dispatchers.IO + handler).launch {
                val clientApiCategoryList = clientRequest.getCategories()
                addRetrievedCategories(clientApiCategoryList.toCategoryList())
            }
        }

        private suspend fun addRetrievedCategories(retrievedCategoryList: CategoryList) {
            withContext(Dispatchers.IO) {
                if (categoryList.isEmpty()) {
                    categoryList.addAll(retrievedCategoryList)
                }
            }
        }

        fun retrieveRandomRecipe() {
            CoroutineScope(Dispatchers.IO + handler).launch {
                val clientApiRecipeList = clientRequest.getRandomRecipe()
                addRetrievedRecipes(clientApiRecipeList.toRecipeList())
            }
        }

        private suspend fun addRetrievedRecipes(retrievedRecipeList: RecipeList) {
            withContext(Dispatchers.IO) {
                recipeList.addAll(retrievedRecipeList)
            }
        }
    }
}