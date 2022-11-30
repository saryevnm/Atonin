package com.it.atonin.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Store(
    @PrimaryKey val storeId: Int,
    @ColumnInfo(name = "store_name") val name: String,
    @ColumnInfo(name = "store_owner_id") val ownerId: Int,
    @ColumnInfo(name = "store_phone_number") val phoneNumber: String = "",
    @ColumnInfo(name = "store_address") val address: String = "",
):FilterUIModel(), Parcelable
