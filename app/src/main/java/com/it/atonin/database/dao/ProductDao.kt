package com.it.atonin.database.dao

import androidx.room.*
import com.it.atonin.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Query("SELECT * FROM PRODUCT ORDER BY price DESC")
    fun getProductsByPriceDesc(): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT ORDER BY price ASC")
    fun getProductsByPriceAsc(): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE brand_id IN (:brandIdList) ORDER BY price ASC")
    fun getProductsByBrandPriceAsc(brandIdList: List<Int>): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE brand_id IN (:brandIdList) ORDER BY price DESC")
    fun getProductsByBrandPriceDesc(brandIdList: List<Int>): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE store_id IN (:storeIdList) ORDER BY price ASC")
    fun getProductsByStorePriceAsc(storeIdList: List<Int>): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE store_id IN (:storeIdList) ORDER BY price DESC")
    fun getProductsByStorePriceDesc(storeIdList: List<Int>): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE store_id IN (:storeIdList) AND brand_id IN (:brandIdList) ORDER BY price ASC")
    fun getProductsByStoreAndBrandAsc(
        storeIdList: List<Int>,
        brandIdList: List<Int>
    ): Flow<List<Product>>

    @Query("SELECT * FROM PRODUCT WHERE store_id IN (:storeIdList) AND brand_id IN (:brandIdList) ORDER BY price DESC")
    fun getProductsByStoreAndBrandDesc(
        storeIdList: List<Int>,
        brandIdList: List<Int>
    ): Flow<List<Product>>

}
