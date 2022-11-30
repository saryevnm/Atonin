package com.it.atonin.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true)
    var productId: Long? =null,
    @ColumnInfo(name = "product_name") val name: String,
    @ColumnInfo(name = "store_id_fk") val storeId: Int,
    @ColumnInfo(name = "brand_id_fk") val brandId: Int?,
    @ColumnInfo(name = "product_image") val image: String,
    @ColumnInfo(name = "product_price") val price: String,
    @ColumnInfo(name = "product_date") var date: String,
) : Parcelable
