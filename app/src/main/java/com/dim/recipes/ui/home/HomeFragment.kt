package com.dim.recipes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dim.recipes.R
import com.dim.recipes.ui.TopMarginItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        homeAdapter = HomeRecyclerAdapter()
        val listSubmitted = homeViewModel.submitListToAdapter(homeAdapter)
        if (!listSubmitted) {
            root.findViewById<TextView>(R.id.text_home_header_problem).visibility = View.VISIBLE
            root.findViewById<TextView>(R.id.text_home_problem).visibility = View.VISIBLE
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        home_recycler_view.apply {
            addItemDecoration(TopMarginItemDecoration(30))
            adapter = homeAdapter
        }
    }
}