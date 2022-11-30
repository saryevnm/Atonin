package com.it.atonin.repository

import com.it.atonin.database.AppDataBase

class StoreRepository(private val db: AppDataBase) {

    fun getStoresOfUser(userId: Int) = db.getStoreDao().getStoresOfUser(userId)

    fun getBrands()= db.getBrandDao().getBrands()

    fun getStores() = db.getStoreDao().getStores()

    fun getStoresCount(userId:Int) = db.getStoreDao().getStoresCountOfUser(userId)

    fun getBrandsCount(storeIds:List<Int>) = db.getBrandDao().getBrandsCount(storeIds)

    fun getBrandsOfGivenStores(storeIds: List<Int>) = db.getBrandDao().getBrandsOfGivenStoreIds(storeIds)
}