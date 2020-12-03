package com.dim.recipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.dim.recipes.R

class RecipeFragment : Fragment() {
    private val arguments: RecipeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipe, container, false)

        val recipeName: TextView = root.findViewById(R.id.recipe_name)
        val recipeCategory: TextView = root.findViewById(R.id.recipe_category)
        val recipeArea: TextView = root.findViewById(R.id.recipe_area)
        val recipeImage: ImageView = root.findViewById(R.id.recipe_image)
        val recipeInstructions: TextView = root.findViewById(R.id.recipe_instructions)

        val recipe = arguments.recipe
        recipeName.text = recipe.name
        recipeCategory.text = recipe.category
        recipeArea.text = recipe.area
        recipeInstructions.visibility = View.VISIBLE
        recipeInstructions.text = recipe.instructions

        loadImageIntoImageView(recipe.image, recipeImage, root.context)

        return root
    }
}