package com.example.finance.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.finance.R
import com.example.finance.databinding.FragmentInfoBinding
import com.example.finance.di.App
import com.example.finance.domain.currency.CurrencyModel
import com.example.finance.domain.currency.GetCurrencyUseCase
import com.example.finance.presentation.presenter.CurrencyViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoFragment : Fragment(R.layout.fragment_info) {

    private var binding: FragmentInfoBinding? = null

    private var currency: CurrencyModel? = null

    @Inject
    lateinit var getCurrencyUseCase: GetCurrencyUseCase

    private val viewModel: CurrencyViewModel by viewModels {
        CurrencyViewModel.provideFactory(
            getCurrencyUseCase
        )
    }

    override fun onAttach(context: Context) {
        App.appComponent.injectCurrencyFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInfoBinding.bind(view)

        lifecycleScope.launch {
            viewModel.loadCurrency()
        }
        observeViewModel()

        binding?.run {
            tvUsd.text = currency?.usd.toString()
            tvEuro.text = currency?.eur.toString()
            tvGbp.text = currency?.gbp.toString()
        }
    }

    private fun setCurrency(cur: CurrencyModel) {
        binding?.run {
            tvUsd.text = cur?.usd.toString()
            tvEuro.text = cur?.eur.toString()
            tvGbp.text = cur?.gbp.toString()
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            currencyInfo.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                setCurrency(it)
                currency = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}