package com.it.atonin.ui.home.filter

import androidx.lifecycle.lifecycleScope
import com.it.atonin.R
import com.it.atonin.databinding.FragmentFilterBrandListBinding
import com.it.atonin.model.Brand
import com.it.atonin.ui.base.BaseFragment
import com.it.atonin.ui.home.HomeViewModel
import com.it.atonin.ui.home.filter.adapter.FilterListAdapter
import com.it.atonin.utils.getFragmentNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterBrandListFragment : BaseFragment<FragmentFilterBrandListBinding>() {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private val brandsAdapter: FilterListAdapter by lazy {
        FilterListAdapter {
            onBrandClick(it as Brand)
        }
    }

    private val navController by lazy {
        getFragmentNavController(R.id.filter_nav_host)
    }

    override fun setupView() {
        binding.brandRv.adapter = brandsAdapter
        lifecycleScope.launch {
            homeViewModel.getBrands().collect { brandsList ->
                val data = brandsList.map { brand -> brand.copy() }
                homeViewModel.getCheckedBrands().forEach { checkedOne ->
                    data.firstOrNull { checkedOne == it.brandId }?.isSelected = true
                }
                withContext(Dispatchers.Main) {
                    brandsAdapter.setList(data)
                }
            }
        }

        binding.brandBackContainer.setOnClickListener {
            navController?.popBackStack()
        }
    }

    override fun bindViewModel() {}

    private fun onBrandClick(brand: Brand) {
        when (brand.isSelected) {
            true -> homeViewModel.addSelectedBrand(brand)
            else -> homeViewModel.removeSelectedBrand(brand)
        }
        homeViewModel.getProducts()
    }
}