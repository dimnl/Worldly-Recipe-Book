package com.dim.recipes.data

import com.dim.recipes.data.api.ApiRequestRandomRecipe
import com.dim.recipes.models.recipe.RecipeList
import kotlinx.coroutines.*

class RecipeRepository {
    companion object {
        private var client = RetrofitClient.retrofit
        var recipeList = RecipeList()

        private val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }

        fun retrieveRandomRecipe() {
            CoroutineScope(Dispatchers.IO + handler).launch {
                val clientApiRecipeList = client.getRandomRecipe()
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