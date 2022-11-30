package com.it.atonin.repository

import com.it.atonin.database.AppDataBase
import com.it.atonin.enum.SortTypes
import com.it.atonin.model.Product
import com.it.atonin.model.ProductWithBrandAndStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(private val db: AppDataBase) {

    fun getProducts(
        sortTypes: SortTypes, selectedBrands: List<Int>, selectedStores: List<Int>
    ): Flow<List<ProductWithBrandAndStore>> {
        with(db.getProductDao()) {
            return when {
                selectedBrands.isNotEmpty() && selectedStores.isNotEmpty() -> {
                    when (sortTypes) {
                        SortTypes.BY_PRICE_DESC -> getProductsByStoreAndBrandDesc(
                            selectedBrands, selectedStores
                        )
                        else -> getProductsByStoreAndBrandAsc(
                            selectedBrands, selectedStores
                        )
                    }
                }
                selectedBrands.isNotEmpty() -> {
                    when (sortTypes) {
                        SortTypes.BY_PRICE_DESC -> getProductsByBrandPriceDesc(selectedBrands)
                        else -> getProductsByBrandPriceAsc(selectedBrands)
                    }
                }

                selectedStores.isNotEmpty() -> {
                    when (sortTypes) {
                        SortTypes.BY_PRICE_DESC -> getProductsByStorePriceDesc(selectedStores)
                        else -> getProductsByStorePriceAsc(selectedStores)
                    }
                }
                else -> {
                    when (sortTypes) {
                        SortTypes.BY_PRICE_DESC -> getProductsByPriceDesc()
                        else -> getProductsByPriceAsc()
                    }
                }
            }
        }
    }

    fun createProduct(product: Product) {
        db.getProductDao().addProduct(product)
    }

    fun updateProduct(product: Product) {
        db.getProductDao().updateProduct(product)
    }
}