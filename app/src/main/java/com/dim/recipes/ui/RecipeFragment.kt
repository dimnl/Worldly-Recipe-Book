package com.dim.recipes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dim.recipes.R
import com.dim.recipes.models.Recipe
import kotlinx.android.synthetic.main.layout_recipe_item.view.*
import org.w3c.dom.Text

class RecipeFragment : Fragment() {
    private val arguments: RecipeFragmentArgs by navArgs()
    private lateinit var recipe: Recipe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_recipe, container, false)

        val recipeName: TextView = root.findViewById(R.id.recipe_name)
        val recipeCategory: TextView = root.findViewById(R.id.recipe_category)
        val recipeArea: TextView = root.findViewById(R.id.recipe_area)
        val recipeImage: ImageView = root.findViewById(R.id.recipe_image)
        val recipeInstructions: TextView = root.findViewById(R.id.recipe_instructions)

        recipe = arguments.recipe
        recipeName.setText(recipe.name)
        recipeCategory.setText(recipe.category)
        recipeArea.setText(recipe.area)
        recipeInstructions.visibility = View.VISIBLE
        recipeInstructions.setText(recipe.instructions)

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_internet_recipe)
            .error(R.drawable.ic_internet_recipe)

        Glide.with(root.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(recipe.image)
            .into(recipeImage)

        return root
    }
}