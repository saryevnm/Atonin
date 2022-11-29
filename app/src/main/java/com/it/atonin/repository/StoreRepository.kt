package com.it.atonin.repository

import com.it.atonin.database.AppDataBase

class StoreRepository(private val db: AppDataBase) {

    fun getStores() = db.getStoreDao().getStores()

    fun getBrands()= db.getBrandDao().getBrands()
}