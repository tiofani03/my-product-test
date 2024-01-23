package com.tiooooo.myproduct.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val orderId: String,
    val productName: List<String>,
    val quantity: Map<String, Int>,

    // for ui purpose
    val recipientName: String = "Tio Fani",
    val address: String? = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
) : Parcelable
