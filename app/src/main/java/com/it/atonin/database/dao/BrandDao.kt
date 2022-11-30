package com.it.atonin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.it.atonin.model.Brand
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBrand(brand: Brand)

    @Update
    fun updateBrand(brand: Brand)

    @Query("SELECT * FROM BRAND")
    fun getBrands(): Flow<List<Brand>>

    @Query("SELECT COUNT(brandId) FROM BRAND WHERE brand_owner_id in (:storesIds)")
    fun getBrandsCount(storesIds:List<Int>): Flow<Int>

    @Query("SELECT * FROM BRAND WHERE brand_owner_id in (:storesIds)")
    fun getBrandsOfGivenStoreIds(storesIds:List<Int>): Flow<List<Brand>>
}
