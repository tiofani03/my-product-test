package com.tiooooo.myproduct.data

import com.tiooooo.myproduct.model.Order
import com.tiooooo.myproduct.model.ProductReview
import com.tiooooo.myproduct.utils.States
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ProductRepository {
    // dummy for calling API
    private val sentimentAnalysisApi = SentimentAnalysis
    private val reviewApi = Review.generateDummyReviews()
    private val optimizePackaging = OptimizePacking

    suspend fun analyzeCustomerFeedback(text: String): Flow<States<String>> {
        return flow {
            try {
                //dummy loading
                emit(States.Loading)
                delay(generateDummyLoading())

                //dummy get data from api
                val res = sentimentAnalysisApi.generateDummySentimentAnalysis(text)
                emit(States.Success(res))

            } catch (e: Exception) {
                emit(States.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProductReview(filterByName: String? = null, sortByRating: String? = null): Flow<States<List<ProductReview>>> {
        return flow {
            try {
                //dummy loading
                emit(States.Loading)
                delay(generateDummyLoading())

                //dummy get data from api
                var res = reviewApi

                // filter with name
                if (!filterByName.isNullOrBlank()){
                    res = res.filter { it.name == filterByName }
                }

                // filter by rating
                if (sortByRating == "asc"){
                    res = res.sortedBy { it.rating }
                } else if (sortByRating == "desc"){
                    res = res.sortedByDescending { it.rating }
                }

                emit(States.Success(res))

            } catch (e: Exception) {
                emit(States.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun suggestPackagingStrategy(orders: List<Order>): Flow<States<Map<String, String>>> =
        flow<States<Map<String, String>>> {
            try {
                emit(States.Loading)
                delay(generateDummyLoading())
                val suggestedStrategies = mutableMapOf<String, String>()
                for (order in orders) {
                    val packagingStrategy =
                        optimizePackaging.optimizePackaging(order.productName, order.quantity)
                    suggestedStrategies[order.orderId] = packagingStrategy
                }
                emit(States.Success(suggestedStrategies))
            } catch (e: Exception) {
                emit(States.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

}

