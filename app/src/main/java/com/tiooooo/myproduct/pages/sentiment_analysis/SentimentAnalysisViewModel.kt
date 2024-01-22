package com.tiooooo.myproduct.pages.sentiment_analysis

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiooooo.myproduct.data.ProductRepository
import com.tiooooo.myproduct.utils.States
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class SentimentAnalysisViewModel : ViewModel() {
    private val productRepository = ProductRepository()

    val textSentimentAnalysis = MutableStateFlow("")
    private val _result = MutableLiveData<States<String>>()
    val result = _result

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun analyzeCustomerFeedback() {
        viewModelScope.launch {
            textSentimentAnalysis
                .debounce(300)
                .distinctUntilChanged()
                .filter {
                    it.trim().isNotEmpty()
                }
                .mapLatest {
                    productRepository.analyzeCustomerFeedback(text = it)
                }.collectLatest {
                    it.collectLatest { res ->
                        _result.value = res
                    }
                }
        }
    }
}

