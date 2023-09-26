package com.android.fastfood.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class FoodMenu(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: Double,
    val description: String,
    val imgUrl: String,
    val location: String

): Parcelable
