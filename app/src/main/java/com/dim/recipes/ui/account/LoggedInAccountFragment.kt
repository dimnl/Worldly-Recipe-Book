package com.dim.recipes.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dim.recipes.R
import com.dim.recipes.ui.TopMarginItemDecoration
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_logged_in_account.*

class LoggedInAccountFragment : Fragment() {

    private lateinit var loggedInAccountViewModel: LoggedInAccountViewModel
    private lateinit var loggedInAccountAdapter: LoggedInAccountRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loggedInAccountViewModel =
            ViewModelProvider(this).get(LoggedInAccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_logged_in_account, container, false)
        val buttonSignOut: Button = root.findViewById(R.id.button_sign_out)
        val accountName: TextView = root.findViewById(R.id.account_name)

        buttonSignOut.setOnClickListener {
            loggedInAccountViewModel.signOut(root)
        }

        try {
            accountName.text = FirebaseAuth.getInstance().currentUser!!.displayName.plus("!")
        } catch (exception: NullPointerException) {
        } // in case displayname cannot be set, do nothing

        loggedInAccountAdapter = LoggedInAccountRecyclerAdapter()
        loggedInAccountViewModel.submitListToAdapter(loggedInAccountAdapter)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        account_recycler_view.apply {
            addItemDecoration(TopMarginItemDecoration(30))
            adapter = loggedInAccountAdapter
        }
    }
}