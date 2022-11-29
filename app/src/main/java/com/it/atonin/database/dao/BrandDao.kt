package com.it.atonin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.it.atonin.model.Brand

@Dao
interface BrandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBrand(brand: Brand)

    @Update
    fun updateBrand(brand: Brand)
}
