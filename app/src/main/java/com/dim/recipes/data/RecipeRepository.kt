package com.dim.recipes.data

import com.dim.recipes.api.ApiRequests
import com.dim.recipes.api.RetrofitClient
import com.dim.recipes.models.categories.CategoryList
import com.dim.recipes.models.recipe.Recipe
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
        var recipeListByCategory = RecipeList()

        private val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }

        fun seedDatabase(recipes: Int) {
            repeat(recipes) {
                CoroutineScope(Dispatchers.IO + handler).launch {
                    retrieveRandomRecipe()
                }
            }

            CoroutineScope(Dispatchers.IO + handler).launch {
                retrieveCategories()
            }
        }

        suspend fun retrieveRandomRecipe() {
            val clientApiRecipeList = clientRequest.getRandomRecipe()
            addRetrievedRecipes(clientApiRecipeList.toRecipeList())
        }

        private suspend fun addRetrievedRecipes(retrievedRecipeList: RecipeList) {
            withContext(Dispatchers.Main) {
                recipeList.addAll(retrievedRecipeList)
            }
        }

        private suspend fun retrieveCategories() {
            val clientApiCategoryList = clientRequest.getCategories()
            addRetrievedCategories(clientApiCategoryList.toCategoryList())
        }

        private suspend fun addRetrievedCategories(retrievedCategoryList: CategoryList) {
            if (categoryList.isEmpty()) {
                withContext(Dispatchers.IO) {
                    categoryList.addAll(retrievedCategoryList)
                }
            }
        }

        suspend fun filterByCategory(category: String) {
            CoroutineScope(Dispatchers.IO + handler).launch {
                val clientApiRecipeListByCategory = clientRequest.filterByCategory(category)
                addRetrievedRecipesByCategory(clientApiRecipeListByCategory.toRecipeByCategoryList())
            }.join()
        }

        private suspend fun addRetrievedRecipesByCategory(retrievedRecipeListByCategory: RecipeList) {
            withContext(Dispatchers.Main) {
                recipeListByCategory.clear()
                recipeListByCategory.addAll(retrievedRecipeListByCategory)
            }
        }

        suspend fun searchRecipe(recipeName: String): Recipe {
            val apiRecipeList = clientRequest.searchRecipe(recipeName)
            return apiRecipeList.toRecipeList()[0]
        }

        suspend fun lookupRecipe(recipeId: String): Recipe {
            val apiRecipeList = clientRequest.lookupRecipe(recipeId)
            return apiRecipeList.toRecipeList()[0]
        }
    }
}