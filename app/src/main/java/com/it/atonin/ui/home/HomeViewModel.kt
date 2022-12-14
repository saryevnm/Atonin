package com.it.atonin.ui.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.it.atonin.enum.SortTypes
import com.it.atonin.model.Brand
import com.it.atonin.model.ProductWithBrandAndStore
import com.it.atonin.model.Store
import com.it.atonin.repository.ProductRepository
import com.it.atonin.repository.StoreRepository
import com.it.atonin.ui.base.BaseViewModel
import com.it.atonin.utils.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val storeRepository: StoreRepository,
    app: Application
) :
    BaseViewModel(app) {

    val onBackPressed by lazy { SingleLiveEvent<Boolean>() }

    private val selectedBrands = arrayListOf<Int>()
    private val selectedStores = arrayListOf<Int>()
    private var sortType = SortTypes.BY_PRICE_DESC


    private val _flowProducts = MutableStateFlow<List<ProductWithBrandAndStore>>(listOf())
    val flowProducts: Flow<List<ProductWithBrandAndStore>> = _flowProducts

    fun getProducts() {
        viewModelScope.launch {
            productRepository.getProducts(sortType, selectedBrands, selectedStores).collect {
                _flowProducts.emit(it)
            }
        }
    }

    fun getStores() = storeRepository.getStores()

    fun getBrands() = storeRepository.getBrands()

    fun setSortType(sortTypes: SortTypes) {
        sortType = sortTypes
    }

    fun getCheckedBrands() = selectedBrands

    fun addSelectedBrand(brand: Brand) {
        selectedBrands.add(brand.brandId)
    }

    fun removeSelectedBrand(brand: Brand) {
        val index = selectedBrands.indexOfFirst { it == brand.brandId }
        selectedBrands.removeAt(index)
    }

    fun addSelectedStore(store: Store) {
        selectedStores.add(store.storeId)
    }

    fun removeSelectedStore(store: Store) {
        val index = selectedStores.indexOfFirst { it == store.storeId }
        selectedStores.removeAt(index)
    }

    fun getCheckedStores() = selectedStores
}