package com.it.atonin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Brand(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "owner_id") val ownerId: Int,
    @ColumnInfo(name = "invented_date") val invented_date: String,

    ) : FilterUIModel()
