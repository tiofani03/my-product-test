package com.tiooooo.myproduct.data

import com.tiooooo.myproduct.model.Order
import com.tiooooo.myproduct.model.ProductReview
import com.tiooooo.myproduct.utils.States
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun analyzeCustomerFeedback(text: String): Flow<States<String>>
    suspend fun getProductReview(
        filterByName: String? = null,
        sortByRating: String? = null,
    ): Flow<States<List<ProductReview>>>

    suspend fun suggestPackagingStrategy(orders: List<Order>): Flow<States<Map<String, String>>>

    suspend fun getOrderList(): Flow<States<List<Order>>>

}

