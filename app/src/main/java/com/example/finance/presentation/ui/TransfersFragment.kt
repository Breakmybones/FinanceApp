package com.example.finance.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finance.R
import com.example.finance.data.local.TransactionLocal
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.data.response.user.response.UserTransactionsResponse
import com.example.finance.databinding.FragmentHomeBinding
import com.example.finance.databinding.FragmentTransfersBinding
import com.example.finance.di.App
import com.example.finance.domain.user.usecase.GetUserTransactionsUseCase
import com.example.finance.presentation.presenter.HomeViewModel
import com.example.finance.presentation.presenter.TransfersViewModel
import com.example.finance.presentation.ui.recycler.TransfersAdapter
import javax.inject.Inject

class TransfersFragment : Fragment(R.layout.fragment_transfers) {

    private var binding: FragmentTransfersBinding? = null

    private var userTransactions: List<TransactionLocal>? = null

    lateinit var repository: UserLocalRepository

    private var adapter: TransfersAdapter? = null

    @Inject
    lateinit var getUserTransferUseCase: GetUserTransactionsUseCase

    private val viewModel: TransfersViewModel by viewModels {
        TransfersViewModel.provideFactory(
            getUserTransferUseCase
        )
    }

    override fun onAttach(context: Context) {
        App.appComponent.injectTransfersFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTransfersBinding.bind(view)

        adapter = TransfersAdapter()
        binding?.rvTaro?.adapter = adapter
        binding?.rvTaro?.layoutManager = LinearLayoutManager(requireContext())
        binding?.btnClose?.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }

        observeViewModel()
        repository = UserLocalRepository(requireContext())

        viewModel.getUserTransactionsFromApi(repository)
    }

    private fun setListAdapterConfig() {
        binding?.rvTaro?.adapter = adapter
        adapter?.submitList(userTransactions)
    }

    private fun observeViewModel() {
        with(viewModel) {
            userTransactionsLiveData.observe(viewLifecycleOwner) {
                if (it == null) return@observe
            }
            userTransactionsBdLiveData.observe(viewLifecycleOwner) {
                userTransactions = it as List<TransactionLocal>?
                setListAdapterConfig()
            }
        }
    }
}