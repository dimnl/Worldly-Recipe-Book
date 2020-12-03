package com.dim.recipes.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.dim.recipes.R
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.ui.TopMarginItemDecoration
import com.dim.recipes.ui.home.HomeRecyclerAdapter
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_logged_in_account.*

class LoggedInAccountFragment : Fragment() {
    private lateinit var homeAdapter: HomeRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_logged_in_account, container, false)
        val buttonSignOut: Button = root.findViewById(R.id.button_sign_out)
        val accountName: TextView = root.findViewById(R.id.account_name)

        buttonSignOut.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext())
        }

        try {
            accountName.text = FirebaseAuth.getInstance().currentUser!!.displayName.plus("!")
        } catch (exception: NullPointerException) {
        } // in case displayname cannot be set, do nothing

        homeAdapter = HomeRecyclerAdapter()
        if (!RecipeRepository.recipeList.isNullOrEmpty()) {
            homeAdapter.submitList(RecipeRepository.recipeList)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        account_recycler_view.apply {
            val topMarginItemDecoration = TopMarginItemDecoration(30)
            addItemDecoration(topMarginItemDecoration)
            adapter = homeAdapter
        }
    }
}