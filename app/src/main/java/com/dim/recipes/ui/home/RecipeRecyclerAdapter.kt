package com.dim.recipes.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dim.recipes.R
import com.dim.recipes.models.Recipe
import kotlinx.android.synthetic.main.layout_recipe_item.view.*

class RecipeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Recipe> = ArrayList()


    fun submitList(recipeList: List<Recipe>){ // for testing
        items = recipeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_recipe_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is RecipeViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RecipeViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName = itemView.recipe_name
        val recipeCategory = itemView.recipe_category
        val recipeArea = itemView.recipe_area
        val recipeImage = itemView.recipe_image

        fun bind(recipe: Recipe) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(recipe.image)
                .into(recipeImage)

            recipeName.setText(recipe.name)
            recipeCategory.setText(recipe.category)
            recipeArea.setText(recipe.area)
        }
    }
}