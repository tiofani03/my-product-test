package com.tiooooo.myproduct.model

data class Order(
    val orderId: String,
    val productName: List<String>,
    val quantity: Map<String, Int>,
)
