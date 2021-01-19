package com.example.android.gdgfinder.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.gdgfinder.R
import com.example.android.gdgfinder.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private val navigateToGdgListObserver by lazy {
        Observer<Boolean> {
            if(findNavController().currentDestination?.id == R.id.home) {
                findNavController().navigate(R.id.action_home_to_gdgListFragment)
                viewModel.onNavigatedToSearch()
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HomeFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.navigateToSearch.observe(viewLifecycleOwner, navigateToGdgListObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.navigateToSearch.removeObserver(navigateToGdgListObserver)
    }
}
