package com.dim.recipes.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dim.recipes.R
import com.dim.recipes.data.RecipeRepository
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {

    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)

        categoriesAdapter = CategoriesRecyclerAdapter()
        if (RecipeRepository.categoryList.isNullOrEmpty()) {
            text_categories_header_problem.visibility = View.VISIBLE
            text_categories_problem.visibility = View.VISIBLE
        } else {
            categoriesAdapter.submitList(RecipeRepository.categoryList)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        categories_recycler_view.apply {
            adapter = categoriesAdapter
        }
    }
}