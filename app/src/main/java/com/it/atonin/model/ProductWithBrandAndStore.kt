package com.it.atonin.model

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductWithBrandAndStore(
    @Embedded
    val product: Product,

    @Embedded
    val store: Store,

    @Embedded
    val brand: Brand
) : Parcelable