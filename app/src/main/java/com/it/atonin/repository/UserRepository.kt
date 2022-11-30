package com.it.atonin.repository

import com.it.atonin.database.AppDataBase

class UserRepository(private val db: AppDataBase) {

    fun getUser(userId:Int)=
        db.getUserDao().getUser(userId)

}