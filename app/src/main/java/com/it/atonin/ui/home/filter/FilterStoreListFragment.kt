package com.it.atonin.ui.home.filter

import androidx.lifecycle.lifecycleScope
import com.it.atonin.databinding.FragmentFilterStoreListBinding
import com.it.atonin.model.Store
import com.it.atonin.ui.base.BaseFragment
import com.it.atonin.ui.home.HomeViewModel
import com.it.atonin.ui.home.filter.adapter.FilterListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FilterStoreListFragment : BaseFragment<FragmentFilterStoreListBinding>() {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private val storesAdapter: FilterListAdapter by lazy {
        FilterListAdapter {
            onBrandClick(it as Store)
        }
    }

    override fun setupView() {
        binding.storeRv.adapter = storesAdapter
        lifecycleScope.launch {
            homeViewModel.getStores().collect { storesList ->
                val data = storesList.map { store -> store.copy() }
                homeViewModel.getCheckedStores().forEach { checkedOne ->
                    data.firstOrNull { checkedOne == it.id }?.isSelected = true
                }
                storesAdapter.setList(data)
            }
        }
    }

    override fun bindViewModel() {}

    private fun onBrandClick(store: Store) {
        when (store.isSelected) {
            true -> homeViewModel.addSelectedStore(store)
            else -> homeViewModel.removeSelectedStore(store)
        }
        homeViewModel.getProducts()
    }
}