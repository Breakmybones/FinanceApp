package com.example.finance.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finance.R
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.databinding.FragmentLoginBinding
import com.example.finance.di.App
import com.example.finance.domain.user.model.UserModel
import com.example.finance.domain.user.usecase.GetUserIdUseCase
import com.example.finance.domain.user.usecase.GetUserUseCase
import com.example.finance.presentation.presenter.LoginViewModel
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null

    private var userData: UserModel? = null

    lateinit var repository: UserLocalRepository

    @Inject
    lateinit var getUserIdUseCase: GetUserIdUseCase

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModel.provideFactory(
            getUserIdUseCase,
            getUserUseCase
        )
    }

    override fun onAttach(context: Context) {
        App.appComponent.injectLoginFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        repository = UserLocalRepository(requireContext())

        binding?.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                viewModel.loginUser(requireContext(), email, password, repository, findNavController())
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            userIdLiveData.observe(viewLifecycleOwner) {
                if (it == null) return@observe
            }
            userLiveData.observe(viewLifecycleOwner) { user ->
                user?.let {
                    userData = it
                }
            }
        }
    }
}