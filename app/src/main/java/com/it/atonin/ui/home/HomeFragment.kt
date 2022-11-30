package com.it.atonin.ui.home

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.it.atonin.R
import com.it.atonin.databinding.FragmentHomeBinding
import com.it.atonin.enum.SortTypes
import com.it.atonin.model.ProductWithBrandAndStore
import com.it.atonin.ui.base.BaseFragment
import com.it.atonin.ui.create.CreateFragment.Companion.PRODUCT
import com.it.atonin.ui.home.adapter.ProductAdapter
import com.it.atonin.utils.show
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

@SuppressLint("RtlHardcoded")
class HomeFragment : BaseFragment<FragmentHomeBinding>(), PopupMenu.OnMenuItemClickListener {

    private val homeViewModel: HomeViewModel by sharedViewModel()

    private val productAdapter by lazy {
        ProductAdapter {
            onProductClick(it)
        }
    }

    override fun setupView() {
        homeViewModel.getProducts()
        initProductList()
        with(binding) {

            filterBtn.setOnClickListener {
                drawerLayout.show()
            }

            sortTypeTxt.setOnClickListener {
                val popUp = PopupMenu(context, it)
                with(popUp) {
                    setOnMenuItemClickListener(this@HomeFragment)
                    inflate(R.menu.menu_sort_list)
                    show()
                }
            }
        }

    }

    private fun initProductList() {
        binding.productList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }

    private fun onProductClick(product: ProductWithBrandAndStore) {
        findNavController().navigate(R.id.createFragment, bundleOf(PRODUCT to product))
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        with(binding) {
            when (p0?.itemId) {
                R.id.expensive_ones -> {
                    sortTypeTxt.text = context?.getString(R.string.price_desc)
                    homeViewModel.setSortType(SortTypes.BY_PRICE_DESC)
                }
                R.id.cheap_ones -> {
                    sortTypeTxt.text = context?.getString(R.string.price_asc)
                    homeViewModel.setSortType(SortTypes.BY_PRICE_ASC)
                }
            }
        }
        homeViewModel.getProducts()
        return true
    }

    override fun bindViewModel() {
        lifecycleScope.launch {
            homeViewModel.flowProductsNew.collectLatest {
                productAdapter.submitList(ArrayList(it))
            }
        }

        homeViewModel.onBackPressed.observe(viewLifecycleOwner) {
            with(binding.drawerLayout) {
                when {
                    isDrawerOpen(Gravity.RIGHT) -> {
                        closeDrawer(Gravity.RIGHT)
                    }
                    else -> {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}