package com.it.atonin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.it.atonin.database.dao.BrandDao
import com.it.atonin.database.dao.ProductDao
import com.it.atonin.database.dao.StoreDao
import com.it.atonin.database.dao.UserDao
import com.it.atonin.model.Brand
import com.it.atonin.model.Product
import com.it.atonin.model.Store
import com.it.atonin.model.User


@Database(entities = [User::class, Store::class, Brand::class, Product::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getUserDao(): UserDao
    abstract fun getBrandDao(): BrandDao
    abstract fun getStoreDao(): StoreDao
}
