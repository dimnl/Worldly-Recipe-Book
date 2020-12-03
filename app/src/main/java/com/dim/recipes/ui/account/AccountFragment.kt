package com.dim.recipes.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dim.recipes.R
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)
        observeAuthenticationState()
        return root
    }

    private fun observeAuthenticationState() {
        accountViewModel.authenticationState.observe(
            viewLifecycleOwner,
            { authenticationState ->
                when (authenticationState) {
                    AccountViewModel.AuthenticationState.AUTHENTICATED -> {
                        findNavController().navigate(AccountFragmentDirections.actionNavigationAccountToLoggedInAccount())
                    }
                    else -> {
                        button_sign_in.setOnClickListener {
                            findNavController().navigate(
                                AccountFragmentDirections.actionNavigationAccountToLogin()
                            )
                        }
                    }
                }
            })
    }
}