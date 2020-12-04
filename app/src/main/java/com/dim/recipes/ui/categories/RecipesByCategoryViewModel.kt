package com.dim.recipes.ui.categories

import androidx.lifecycle.ViewModel
import com.dim.recipes.data.RecipeRepository

class RecipesByCategoryViewModel: ViewModel() {
    /**
     * Returns a [Boolean] indicating if list was succesfully submitted to the adapter
     */
    fun submitListToAdapter(adapter: RecipesByCategoryRecyclerAdapter): Boolean {
        if (!RecipeRepository.recipeListByCategory.isNullOrEmpty()) {
            adapter.submitList(RecipeRepository.recipeListByCategory)
            adapter.notifyDataSetChanged()
            return true
        } else {
            return false
        }
    }
}