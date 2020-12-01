package com.dim.recipes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dim.recipes.R
import com.dim.recipes.data.DummyDataSource
import com.dim.recipes.data.RecipeRepository
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (RecipeRepository.recipeList.isNullOrEmpty()) {
            text_home_header_problem.visibility = View.VISIBLE
            text_home_problem.visibility = View.VISIBLE
        } else {
            initRecyclerView()
            homeAdapter.submitList(RecipeRepository.recipeList)
        }

//        addDummyDataSet() // to test if adapter is correctly functioning
    }

    private fun addDummyDataSet() { // purely for testing
        val data = DummyDataSource.createDataSet()
        homeAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        home_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)

            val topMarginItemDecoration = TopMarginItemDecoration(30)
            addItemDecoration(topMarginItemDecoration)

            homeAdapter = HomeRecyclerAdapter()
            adapter = homeAdapter

        }
    }
}