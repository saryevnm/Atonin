package com.it.atonin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Store(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "owner_id") val ownerId: Int,
    @ColumnInfo(name = "phone_number") val phoneNumber: String = "",
    @ColumnInfo(name = "address") val address: String = "",
):FilterUIModel()
