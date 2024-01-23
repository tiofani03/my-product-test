package com.tiooooo.myproduct.pages.list_product_review

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiooooo.myproduct.databinding.ActivityListProductReviewBinding
import com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.RadioButtonFilterBottomSheet
import com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.model.DropdownItem
import com.tiooooo.myproduct.utils.States
import com.tiooooo.myproduct.utils.handleStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListProductReviewBinding
    private val viewModel: ListProductReviewViewModel by viewModels()

    private val productReviewAdapter = ListProductReviewAdapter()
    private lateinit var selectedSortFilter: DropdownItem
    private lateinit var selectedByProduct: DropdownItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        getProductReview()
        observeViewModel()
    }

    private fun initView() {
        selectedSortFilter = viewModel.getFilterSortBy().first()
        selectedByProduct = viewModel.getFilterByProduct().first()

        with(binding) {
            rvReviewProduct.adapter = productReviewAdapter
            rvReviewProduct.layoutManager = LinearLayoutManager(this@ListProductReviewActivity)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val filterBySortBottomSheet = RadioButtonFilterBottomSheet.newInstance(
            title = "Sort By Rating",
            filterList = viewModel.getFilterSortBy(),
            selectedFilter = selectedSortFilter,
        )

        val filterByProductBottomSheet = RadioButtonFilterBottomSheet.newInstance(
            title = "Sort By Product",
            filterList = viewModel.getFilterByProduct(),
            selectedFilter = selectedByProduct,
        )

        binding.tvFilterByRating.apply {
            setOnClickListener {
                if (filterBySortBottomSheet.isAdded) return@setOnClickListener

                filterBySortBottomSheet.show(
                    supportFragmentManager, filterBySortBottomSheet.tag
                )
                filterBySortBottomSheet.onFilterClick = {
                    val textTitle = if (it.id == 0) "Sort By" else it.title
                    text = textTitle
                    selectedSortFilter = it
                    it.slug?.let {
                        getProductReview()
                    }
                }
            }
        }

        binding.tvFilterByProduct.apply {
            setOnClickListener {
                if (filterByProductBottomSheet.isAdded) return@setOnClickListener

                filterByProductBottomSheet.show(
                    supportFragmentManager, filterByProductBottomSheet.tag
                )
                filterByProductBottomSheet.onFilterClick = {
                    val textTitle = if (it.id == 0) "Choose Product" else it.title
                    text = textTitle
                    selectedByProduct = it
                    it.slug?.let {
                        getProductReview()
                    }
                }
            }
        }
    }


    private fun getProductReview() {
        viewModel.getProductReview(
            filterByName = selectedByProduct.slug.orEmpty(),
            sortByRating = selectedSortFilter.slug.orEmpty(),
        )
    }

    private fun observeViewModel() {
        viewModel.productReview.observe(this) { state ->
            binding.pbLoading.isVisible = state is States.Loading
            binding.rvReviewProduct.isVisible = state !is States.Loading && state !is States.Error
            state.handleStates(
                successBlock = {
                    binding.rvReviewProduct.isVisible = it.isNotEmpty()
                    productReviewAdapter.submitList(null)
                    productReviewAdapter.submitList(it)
                    binding.rvReviewProduct.scrollToPosition(0)
                },
                errorBlock = { },
                emptyBlock = { },
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
