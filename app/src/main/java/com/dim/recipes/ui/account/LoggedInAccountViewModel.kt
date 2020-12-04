package com.dim.recipes.ui.account

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.dim.recipes.api.FavouriteRecipeFirebaseDatabase
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.models.recipe.RecipeList
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoggedInAccountViewModel : ViewModel() {
    fun signOut(view: View) {
        viewModelScope.launch {
            AuthUI.getInstance().signOut(view.context)
            FavouriteRecipeFirebaseDatabase.userLoggedIn = false
            delay(300) // give some time to sign out
            view.findNavController()
                .navigate(LoggedInAccountFragmentDirections.actionLoggedInAccountToNavigationAccount())
        }
    }

    fun submitListToAdapter(adapter: LoggedInAccountRecyclerAdapter) {
        val recipeList = RecipeList()

        try {
            viewModelScope.launch {
                delay(300) // give some time for existing favourites to be loaded
                FavouriteRecipeFirebaseDatabase.favouriteRecipeIdList.map {
                    val recipe = RecipeRepository.lookupRecipe(it)
                    recipeList.add(recipe)
                }
                adapter.submitList(recipeList)
                adapter.notifyDataSetChanged()
            }
        } catch (exception: Exception) {
            Log.w("LoggedInAccountViewModelScope", "Exception when updating favourite recipes")
        }
    }
}