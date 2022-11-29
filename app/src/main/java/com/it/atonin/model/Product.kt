package com.it.atonin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "store_id") val storeId: Int,
    @ColumnInfo(name = "brand_id") val brandId: Int?,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "date") val date: String,
)
