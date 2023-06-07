package com.example.finance.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.finance.R
import com.example.finance.data.local.UserLocalRepository
import com.example.finance.databinding.FragmentProfileBinding
import com.example.finance.di.App
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null

    lateinit var repository: UserLocalRepository

    override fun onAttach(context: Context) {
        App.appComponent.injectProfileFragment(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
        repository = UserLocalRepository(requireContext())

        lifecycleScope.launch {
            val userBd = repository.getUser()
            setData(userBd?.email!!, userBd.firstName!!, userBd.lastName!!, userBd.dateOfBirth!!, userBd.region!!)
        }

        binding?.run {
            btnClose.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }
            btnExit.setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

    private fun setData(email: String, name: String, lastName: String, dateOfBirth: String, region: String) {
        binding?.run {
            tvUsername.text = name
            tvSurname.text = lastName
            tvBirth.text = dateOfBirth
            tvRegion.text = region
            tvUsernameFull.text = email
        }
    }
}