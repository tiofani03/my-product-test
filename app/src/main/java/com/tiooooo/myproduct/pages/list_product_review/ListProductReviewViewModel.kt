package com.tiooooo.myproduct.pages.list_product_review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiooooo.myproduct.data.ProductRepository
import com.tiooooo.myproduct.model.ProductReview
import com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.model.DropdownItem
import com.tiooooo.myproduct.utils.States
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListProductReviewViewModel : ViewModel() {
    private val productRepository = ProductRepository()

    private val _productReview = MutableLiveData<States<List<ProductReview>>>()
    val productReview: LiveData<States<List<ProductReview>>> = _productReview

    fun getProductReview(
        filterByName: String? = null,
        sortByRating: String = "desc",
    ) {
        viewModelScope.launch {
            productRepository.getProductReview(
                filterByName = filterByName,
                sortByRating = sortByRating,
            ).collectLatest {
                _productReview.value = it
            }
        }
    }

    fun getFilterSortBy() = listOf(
        DropdownItem(0, "No Filter", ""),
        DropdownItem(1, "Highest", "desc"),
        DropdownItem(2, "Lowest", "asc"),
    )

    fun getFilterByProduct() = listOf(
        DropdownItem(0, "All Product", ""),
        DropdownItem(1, "ProductA", "ProductA"),
        DropdownItem(2, "ProductB", "ProductB"),
        DropdownItem(3, "ProductC", "ProductC"),
    )


}
