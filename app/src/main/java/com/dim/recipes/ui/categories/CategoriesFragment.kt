package com.dim.recipes.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dim.recipes.R
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.ui.home.TopMarginItemDecoration
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment() {

    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // categoriesViewModel =
                // ViewModelProvider(this).get(CategoriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        // val textView: TextView = root.findViewById(R.id.text_categories)
        // categoriesViewModel.text.observe(viewLifecycleOwner, Observer {
        //     textView.text = it
        // })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (RecipeRepository.categoryList.isNullOrEmpty()) {
            text_categories_header_problem.visibility = View.VISIBLE
            text_categories_problem.visibility = View.VISIBLE
        } else {
            initRecyclerView()
            categoriesAdapter.submitList(RecipeRepository.categoryList)
        }
    }

    private fun initRecyclerView() {
        categories_recycler_view.apply {

            val topMarginItemDecoration = TopMarginItemDecoration(30)
            addItemDecoration(topMarginItemDecoration)

            categoriesAdapter = CategoriesRecyclerAdapter()
            adapter = categoriesAdapter

        }
    }
}