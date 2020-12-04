package com.dim.recipes.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dim.recipes.R
import com.dim.recipes.ui.TopMarginItemDecoration
import kotlinx.android.synthetic.main.fragment_recipes_by_category.*

class RecipesByCategoryFragment : Fragment() {

    private lateinit var recipesByCategoryViewModel: RecipesByCategoryViewModel
    private lateinit var recipesByCategoryAdapter: RecipesByCategoryRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        recipesByCategoryViewModel =
            ViewModelProvider(this).get(RecipesByCategoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recipes_by_category, container, false)

        recipesByCategoryAdapter = RecipesByCategoryRecyclerAdapter()
        val listSubmitted = recipesByCategoryViewModel.submitListToAdapter(recipesByCategoryAdapter)
        if (!listSubmitted) {
            text_recipes_by_category_header_problem.visibility = View.VISIBLE
            text_recipes_by_category_problem.visibility = View.VISIBLE
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recipes_by_category_recycler_view.apply {
            addItemDecoration(TopMarginItemDecoration(30))
            adapter = recipesByCategoryAdapter
        }
    }
}