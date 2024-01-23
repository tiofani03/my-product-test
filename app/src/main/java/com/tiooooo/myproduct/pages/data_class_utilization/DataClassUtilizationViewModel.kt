package com.tiooooo.myproduct.pages.data_class_utilization

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiooooo.myproduct.data.ProductRepository
import com.tiooooo.myproduct.model.Order
import com.tiooooo.myproduct.utils.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataClassUtilizationViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _productReview = MutableLiveData<States<Map<String, String>>>()
    val productReview = _productReview

    private val _orderList = MutableLiveData<States<List<Order>>>()
    val orderList = _orderList

    fun suggestPackagingStrategy(orderList: List<Order>) {
        viewModelScope.launch {
            productRepository.suggestPackagingStrategy(orderList).collectLatest {
                _productReview.value = it
            }
        }
    }

    fun getOrderList() {
        viewModelScope.launch {
            productRepository.getOrderList().collectLatest {
                _orderList.value = it
            }
        }
    }
}
