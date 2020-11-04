package com.dim.recipes.data

import com.dim.recipes.models.ApiRecipe
import com.dim.recipes.models.ApiRecipeList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeRepository {
    companion object {
        var client: ApiRequestRandomRecipe = RetrofitClient.retrofit
        var apiRecipeList: ApiRecipeList = ApiRecipeList(ArrayList<ApiRecipe>())

        fun retrieveRandomRecipe() {
            CoroutineScope(Dispatchers.IO).launch {
                val clientApiRecipeList = client.getRandomRecipe()
                addRecipe(clientApiRecipeList.get(0))
            }
        }

        private suspend fun addRecipe(recipe: ApiRecipe) {
            withContext(Dispatchers.IO) {
                apiRecipeList.add(recipe)
            }
        }
    }
}