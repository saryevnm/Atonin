package com.it.atonin.database.dao

import androidx.room.*
import com.it.atonin.model.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addStore(store: Store)

    @Update
    fun updateStore(store: Store)

    @Query("SELECT * FROM STORE WHERE store_owner_id =:userId")
    fun getStoresOfUser(userId: Int): Flow<List<Store>>

    @Query("SELECT * FROM STORE")
    fun getStores(): Flow<List<Store>>

    @Query("SELECT COUNT(storeId) FROM STORE WHERE store_owner_id =:userId")
    fun getStoresCountOfUser(userId:Int): Flow<Int>
}
