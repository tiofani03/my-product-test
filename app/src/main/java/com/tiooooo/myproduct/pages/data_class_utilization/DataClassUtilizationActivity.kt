package com.tiooooo.myproduct.pages.data_class_utilization

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiooooo.myproduct.databinding.ActivityDataClassUtilizationBinding
import com.tiooooo.myproduct.pages.data_class_utilization.result.ResultBottomSheet
import com.tiooooo.myproduct.utils.States
import com.tiooooo.myproduct.utils.handleStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataClassUtilizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataClassUtilizationBinding
    private val viewModel: DataClassUtilizationViewModel by viewModels()
    private lateinit var orderAdapter: OrderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataClassUtilizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        orderAdapter = OrderAdapter()

        initView()
        observeViewModel()
    }

    private fun initView() {
        viewModel.getOrderList()

        binding.rvOrder.apply {
            layoutManager = LinearLayoutManager(this@DataClassUtilizationActivity)
            adapter = orderAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.orderList.observe(this) { state ->
            binding.pbLoading.isVisible = state is States.Loading
            binding.rvOrder.isVisible = state !is States.Loading && state !is States.Error
            binding.bottomContainer.isVisible = state !is States.Loading && state !is States.Error

            state.handleStates(
                successBlock = {
                    orderAdapter.setData(it)
                    val textOrder = if (it.size == 1) "order" else "orders"
                    binding.tvTotal.text = "${it.size} $textOrder"
                    val filterByProductBottomSheet = ResultBottomSheet.newInstance(
                        orderList = it
                    )

                    binding.btnSeeResult.setOnClickListener {
                        if (filterByProductBottomSheet.isAdded) return@setOnClickListener

                        filterByProductBottomSheet.show(
                            supportFragmentManager,
                            filterByProductBottomSheet.tag
                        )
                    }
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
