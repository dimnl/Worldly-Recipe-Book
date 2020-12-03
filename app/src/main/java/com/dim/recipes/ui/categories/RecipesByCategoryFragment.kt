package com.dim.recipes.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dim.recipes.R
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.ui.TopMarginItemDecoration
import kotlinx.android.synthetic.main.fragment_recipes_by_category.*

class RecipesByCategoryFragment : Fragment() {
    private lateinit var recipesByCategoryAdapter: RecipesByCategoryRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_recipes_by_category, container, false)

        recipesByCategoryAdapter = RecipesByCategoryRecyclerAdapter()
        if (RecipeRepository.recipeListByCategory.isNullOrEmpty()) {
            text_recipes_by_category_header_problem.visibility = View.VISIBLE
            text_recipes_by_category_problem.visibility = View.VISIBLE
        } else {
            recipesByCategoryAdapter.submitList(RecipeRepository.recipeListByCategory)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recipes_by_category_recycler_view.apply {
            val topMarginItemDecoration = TopMarginItemDecoration(30)
            addItemDecoration(topMarginItemDecoration)

            adapter = recipesByCategoryAdapter
        }
    }
}