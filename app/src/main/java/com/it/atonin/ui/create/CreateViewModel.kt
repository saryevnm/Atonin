package com.it.atonin.ui.create

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.it.atonin.model.Brand
import com.it.atonin.model.Product
import com.it.atonin.model.Store
import com.it.atonin.repository.ProductRepository
import com.it.atonin.repository.StoreRepository
import com.it.atonin.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CreateViewModel(
    private val productRepository: ProductRepository,
    private val storeRepository: StoreRepository,
    app: Application
) :
    BaseViewModel(app) {

    private val _flowBrands = MutableStateFlow<List<Brand>>(listOf())
    val flowBrands: Flow<List<Brand>> = _flowBrands

    private val _flowStores = MutableStateFlow<List<Store>>(listOf())
    val flowStores: Flow<List<Store>> = _flowStores

    fun getStoresOfUser() {
        viewModelScope.launch {
            storeRepository.getStoresOfUser(0).collect {
                _flowStores.emit(it)
            }
        }
    }


    fun getBrands() {
        viewModelScope.launch {
            storeRepository.getStoresOfUser(0).collect { stores ->
                val storeIds = stores.map { it.storeId }
                storeRepository.getBrandsOfGivenStores(storeIds).collect {
                    _flowBrands.emit(it)
                }
            }
        }
    }

    fun createProduct(product: Product) {
        productRepository.createProduct(product)
    }

    fun updateProduct(product: Product) {
        productRepository.updateProduct(product)
    }
}