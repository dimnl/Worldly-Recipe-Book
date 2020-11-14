package com.dim.recipes.data

import com.dim.recipes.models.RecipeList
import kotlinx.coroutines.*

class RecipeRepository {
    companion object {
        var client: ApiRequestRandomRecipe = RetrofitClient.retrofit
        var recipeList = RecipeList()

        val handler = CoroutineExceptionHandler { _, exception ->
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