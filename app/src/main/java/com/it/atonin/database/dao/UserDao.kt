package com.it.atonin.database.dao

import androidx.room.*
import com.it.atonin.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * FROM USER WHERE id =:userId")
    fun getUser(userId: Int): Flow<User>
}
