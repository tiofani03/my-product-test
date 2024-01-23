package com.tiooooo.myproduct.pages.list_product_review

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tiooooo.myproduct.data.ProductRepository
import com.tiooooo.myproduct.helper.CoroutineTestRule
import com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.model.DropdownItem
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListProductReviewViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var dispatcherRule = CoroutineTestRule()

    @get:Rule
    var mockkRule = MockKRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var productRepository: ProductRepository

    private lateinit var viewModel: ListProductReviewViewModel

    private val listByRating = listOf(
        DropdownItem(0, "No Filter", ""),
        DropdownItem(1, "Highest", "desc"),
        DropdownItem(2, "Lowest", "asc"),
    )

    private val listByProduct = listOf(
        DropdownItem(0, "All Product", ""),
        DropdownItem(1, "ProductA", "ProductA"),
        DropdownItem(2, "ProductB", "ProductB"),
        DropdownItem(3, "ProductC", "ProductC"),
    )

    @Before
    fun setUp() {
        viewModel = ListProductReviewViewModel(
            productRepository = productRepository,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `get filter by product`() = runTest {
        val actual = viewModel.getFilterByProduct()
        assertEquals(listByProduct, actual)
    }

    @Test
    fun `get data filter by rating`() = runTest {
        val actual = viewModel.getFilterSortBy()
        assertEquals(listByRating, actual)
    }
}
