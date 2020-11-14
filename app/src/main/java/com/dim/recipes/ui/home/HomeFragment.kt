package com.dim.recipes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dim.recipes.data.DummyDataSource
import com.dim.recipes.R
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (RecipeRepository.recipeList.isNullOrEmpty()) {
            text_home_header_problem.visibility = View.VISIBLE
            text_home_problem.visibility = View.VISIBLE
        } else {
            initRecyclerView()
            recipeAdapter.submitList(RecipeRepository.recipeList)
        }

//        addDummyDataSet()
//        recipeAdapter.submitList(RecipeRepository.recipeList)
    }

    private fun addDummyDataSet() { // purely for testing
        val data = DummyDataSource.createDataSet()
        recipeAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)

            val topMarginItemDecoration = TopMarginItemDecoration(30)
            addItemDecoration(topMarginItemDecoration)

            recipeAdapter = RecipeRecyclerAdapter()
            adapter = recipeAdapter

        }
    }
}