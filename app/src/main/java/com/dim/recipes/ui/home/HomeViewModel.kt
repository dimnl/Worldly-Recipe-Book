package com.dim.recipes.ui.home

import androidx.lifecycle.ViewModel
import com.dim.recipes.data.RecipeRepository

class HomeViewModel : ViewModel() {
    /**
     * Returns a [Boolean] indicating if list was succesfully submitted to the adapter
     */
    fun submitListToAdapter(adapter: HomeRecyclerAdapter): Boolean {
        if (!RecipeRepository.recipeList.isNullOrEmpty()) {
            adapter.submitList(RecipeRepository.recipeList)
            adapter.notifyDataSetChanged()
            return true
        } else {
            return false
        }
    }
}
