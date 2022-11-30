package com.it.atonin.database.dao

import androidx.room.*
import com.it.atonin.model.Product
import com.it.atonin.model.ProductWithBrandAndStore
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk ORDER BY product_price DESC")
    fun getProductsByPriceDesc(): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk ORDER BY product_price ASC")
    fun getProductsByPriceAsc(): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk  WHERE brandId IN (:brandIdList) ORDER BY product_price ASC")
    fun getProductsByBrandPriceAsc(brandIdList: List<Int>): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk WHERE brandId IN (:brandIdList) ORDER BY product_price DESC")
    fun getProductsByBrandPriceDesc(brandIdList: List<Int>): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk  WHERE storeId IN (:storeIdList) ORDER BY product_price ASC")
    fun getProductsByStorePriceAsc(storeIdList: List<Int>): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk  WHERE storeId IN (:storeIdList) ORDER BY product_price DESC")
    fun getProductsByStorePriceDesc(storeIdList: List<Int>): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk WHERE storeId IN (:storeIdList) AND brandId IN (:brandIdList) ORDER BY product_price ASC")
    fun getProductsByStoreAndBrandAsc(
        storeIdList: List<Int>, brandIdList: List<Int>
    ): Flow<List<ProductWithBrandAndStore>>

    @Query("SELECT p.*,s.*,b.* FROM PRODUCT as p INNER JOIN STORE as s ON  s.storeId == p.store_id_fk INNER JOIN BRAND as b ON b.brandId == p.brand_id_fk WHERE storeId IN (:storeIdList) AND brandId IN (:brandIdList) ORDER BY product_price DESC")
    fun getProductsByStoreAndBrandDesc(
        storeIdList: List<Int>, brandIdList: List<Int>
    ): Flow<List<ProductWithBrandAndStore>>
}
