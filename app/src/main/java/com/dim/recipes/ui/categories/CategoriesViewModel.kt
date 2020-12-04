package com.dim.recipes.ui.categories

import androidx.lifecycle.ViewModel
import com.dim.recipes.data.RecipeRepository

class CategoriesViewModel : ViewModel() {
    /**
     * Returns a [Boolean] indicating if list was succesfully submitted to the adapter
     */
    fun submitListToAdapter(adapter: CategoriesRecyclerAdapter): Boolean {
        if (!RecipeRepository.categoryList.isNullOrEmpty()) {
            adapter.submitList(RecipeRepository.categoryList)
            adapter.notifyDataSetChanged()
            return true
        } else {
            return false
        }
    }
}
