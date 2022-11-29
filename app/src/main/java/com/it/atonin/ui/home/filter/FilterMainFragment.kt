package com.it.atonin.ui.home.filter

import androidx.activity.addCallback
import com.it.atonin.R
import com.it.atonin.databinding.FragmentFilterMainBinding
import com.it.atonin.ui.base.BaseFragment
import com.it.atonin.ui.home.HomeViewModel
import com.it.atonin.utils.getFragmentNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterMainFragment : BaseFragment<FragmentFilterMainBinding>() {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private val navController by lazy {
        getFragmentNavController(R.id.filter_nav_host)
    }

    override fun setupView() {

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            homeViewModel.onBackPressed.postValue(true)
        }

        binding.brandContainer.setOnClickListener {
            navController?.navigate(R.id.filterBrandListFragment)
        }
        binding.storeContainer.setOnClickListener {
            navController?.navigate(R.id.filterStoreListFragment)
        }
    }

    override fun bindViewModel() {}
}