package com.example.android.gdgfinder.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.android.gdgfinder.R
import com.example.android.gdgfinder.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var navObserver: Observer<Boolean>

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        navObserver = Observer<Boolean> { shouldNavigate ->
            if (shouldNavigate == true) {
                val navController = binding.root.findNavController()
                navController.navigate(R.id.action_home_to_gdgListFragment)
                viewModel.onNavigatedToSearch()
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.navigateToSearch.observe(viewLifecycleOwner, navObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.navigateToSearch.removeObserver(navObserver)
    }
}
