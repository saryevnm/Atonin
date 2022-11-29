package com.it.atonin.ui.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.it.atonin.enum.SortTypes
import com.it.atonin.model.Product
import com.it.atonin.repository.ProductRepository
import com.it.atonin.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepository: ProductRepository, app: Application) :
    BaseViewModel(app) {

    private val selectedBrands = arrayListOf<Int>()
    private val selectedStores = arrayListOf<Int>()
    private var sortType = SortTypes.BY_PRICE_DESC

    private val _flowProducts = MutableStateFlow<List<Product>>(listOf())
    val flowProducts: Flow<List<Product>> = _flowProducts

    fun getProducts() {
        viewModelScope.launch {
            productRepository.getProducts(sortType, selectedBrands, selectedStores).collect {
                _flowProducts.emit(it)
            }
        }
    }

    fun setSortType(sortTypes: SortTypes) {
        sortType = sortTypes
    }
}