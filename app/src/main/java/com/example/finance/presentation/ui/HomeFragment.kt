package com.example.finance.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.finance.R
import com.example.finance.data.local.TransactionLocal
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.data.response.user.response.UserTransactionsResponse
import com.example.finance.databinding.FragmentHomeBinding
import com.example.finance.di.App
import com.example.finance.domain.user.model.UserModel
import com.example.finance.domain.user.usecase.GetUserTransactionsUseCase
import com.example.finance.domain.user.usecase.GetUserUseCase
import com.example.finance.domain.user.usecase.PutTransferUseCase
import com.example.finance.presentation.presenter.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    private var userData: UserModel? = null

    private var userTransactions: UserTransactionsResponse? = null

    lateinit var repository: UserLocalRepository

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    @Inject
    lateinit var getUserTransferUseCase: GetUserTransactionsUseCase

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.provideFactory(
            getUserUseCase,
            getUserTransferUseCase
        )
    }

    override fun onAttach(context: Context) {
        App.appComponent.injectHomeFragment(this)
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        observeViewModel()
        repository = UserLocalRepository(requireContext())
        viewModel.getUserFromApi(requireContext(), repository)
        userData?.let { setUserData(it) }

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUserFromApi(requireContext(), repository)
            userData?.let {
                setUserData(it)
                swipeRefreshLayout.isRefreshing = false
            }
        }
        viewModel.getUserCard(repository)

        binding?.run {
            tvUsername.setOnClickListener {
                findNavController().navigate(R.id.profileFragment)
            }
            recycler.setOnClickListener {
                findNavController().navigate(R.id.transfersFragment)
            }
        }

        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_bottomnav -> {
                    findNavController().navigate(R.id.homeFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.pay_bottomnav -> {
                    findNavController().navigate(R.id.cashFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.inf_bottomnav -> {
                    findNavController().navigate(R.id.infoFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUserData(user: UserModel) {

        binding?.run {
            tvUsername.text = user.firstName + " " + user.lastName
            tvBalance.text = user.balance.toString()
            tvCardNumber.text = user.cardNumber
            tvInvest.text = "100 рублей инвестировано"
            tvCashback.text = "100%"
            Log.e("jkdfkjdf", "jfkjdfkj")
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            userLiveData.observe(viewLifecycleOwner) { user ->
                user?.let {
                    userData = it
                    setUserData(it)
                }
            }
            userTransactionsLiveData.observe(viewLifecycleOwner) { transactions ->
                transactions?.let {
                    userTransactions = it
                }
            }
        }
    }
}