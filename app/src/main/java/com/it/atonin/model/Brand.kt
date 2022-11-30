package com.it.atonin.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Brand(
    @PrimaryKey val brandId: Int,
    @ColumnInfo(name = "brand_name") val name: String,
    @ColumnInfo(name = "brand_image") val image: String?,
    @ColumnInfo(name = "brand_owner_id") val ownerId: Int,
    @ColumnInfo(name = "brand_invented_date") val invented_date: String,

    ) : FilterUIModel(), Parcelable
