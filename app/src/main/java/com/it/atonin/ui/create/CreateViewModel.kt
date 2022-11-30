package com.it.atonin.ui.create

import android.app.Application
import com.it.atonin.model.Product
import com.it.atonin.repository.ProductRepository
import com.it.atonin.repository.StoreRepository
import com.it.atonin.ui.base.BaseViewModel

class CreateViewModel(
    private val productRepository: ProductRepository,
    private val storeRepository: StoreRepository,
    app: Application
) :
    BaseViewModel(app) {

    fun getStores() = storeRepository.getStores()

    fun getBrands() = storeRepository.getBrands()

    fun createProduct(product: Product) {
        productRepository.createProduct(product)
    }

    fun updateProduct(product: Product) {
        productRepository.updateProduct(product)
    }
}