package com.dim.recipes.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dim.recipes.R
import com.dim.recipes.api.FavouriteRecipeFirebaseDatabase
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.models.recipe.Recipe
import com.dim.recipes.models.recipe.RecipeList
import com.dim.recipes.ui.loadImageIntoImageView
import kotlinx.android.synthetic.main.layout_recipe_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesByCategoryRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = RecipeList()

    fun submitList(recipeListByCategory: RecipeList) {
        items = recipeListByCategory
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeByCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecipeByCategoryViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RecipeByCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.recipe_name
        private val recipeCategory: TextView = itemView.recipe_category
        private val recipeArea: TextView = itemView.recipe_area
        private val recipeImage: ImageView = itemView.recipe_image
        private val checkBox: CheckBox = itemView.favourite_checkbox

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val item = items.get(position)
                CoroutineScope(Dispatchers.Main).launch {
                    val recipe = RecipeRepository.lookupRecipe(item.id)
                    v.findNavController().navigate(
                        RecipesByCategoryFragmentDirections.actionRecipesByCategoryToRecipe(recipe)
                    )
                }
            }
            if (FavouriteRecipeFirebaseDatabase.userLoggedIn) {
                checkBox.setOnClickListener {
                    val position: Int = adapterPosition
                    val item = items.get(position)
                    val favId = item.id

                    if (checkBox.isChecked) {
                        FavouriteRecipeFirebaseDatabase.write(favId)
                    } else if (FavouriteRecipeFirebaseDatabase.favouriteRecipeIdList.contains(favId)) {
                        FavouriteRecipeFirebaseDatabase.delete(favId)
                    }
                }
            } else {
                checkBox.visibility = View.GONE
            }
        }

        fun bind(recipe: Recipe) {
            loadImageIntoImageView(recipe.image, recipeImage, itemView.context)
            recipeName.text = recipe.name

            recipeCategory.visibility = View.INVISIBLE
            recipeArea.visibility = View.INVISIBLE

            if (FavouriteRecipeFirebaseDatabase.userLoggedIn) {
                if (FavouriteRecipeFirebaseDatabase.favouriteRecipeIdList.contains(recipe.id)) {
                    checkBox.isChecked = true
                }
            }
        }
    }
}