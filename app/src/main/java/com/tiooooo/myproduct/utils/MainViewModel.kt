package com.tiooooo.myproduct.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiooooo.myproduct.data.ProductRepository
import com.tiooooo.myproduct.model.ProductReview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val productRepository = ProductRepository()

    private val _productReview = MutableLiveData<States<List<ProductReview>>>()
    val productReview: LiveData<States<List<ProductReview>>> = _productReview

    private val _textAnalysis = MutableLiveData<States<String>>()
    val textAnalysis = _textAnalysis

    fun getProductReview(
        sortByName: String? = null,
        sortByRating: String? = "",
    ) {
        viewModelScope.launch {
            productRepository.getProductReview(
                filterByName = sortByName,
                sortByRating = sortByRating,
            ).collectLatest {
                _productReview.value = it
            }
        }
    }

    fun getSentimentAnalysis(
        text: String,
    ) = viewModelScope.launch {
        productRepository.analyzeCustomerFeedback(text).collectLatest {
            _textAnalysis.value = it
        }
    }


}
