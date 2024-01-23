package com.tiooooo.myproduct.pages.data_class_utilization.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tiooooo.myproduct.databinding.BottomSheetResultBinding
import com.tiooooo.myproduct.model.Order
import com.tiooooo.myproduct.pages.data_class_utilization.DataClassUtilizationViewModel
import com.tiooooo.myproduct.pages.data_class_utilization.OrderDetailAdapter
import com.tiooooo.myproduct.pages.data_class_utilization.ProductQuantity
import com.tiooooo.myproduct.utils.States
import com.tiooooo.myproduct.utils.handleStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultBottomSheet : BottomSheetDialogFragment() {
    companion object {
        private const val EXTRA_ORDER = "EXTRA_ORDER"

        fun newInstance(
            orderList: List<Order>,
        ) = ResultBottomSheet().apply {
            val bundle = Bundle()
            bundle.putParcelableArrayList(
                EXTRA_ORDER,
                arrayListOf<Order>().apply { addAll(orderList) }
            )
            arguments = bundle
        }
    }

    private lateinit var binding: BottomSheetResultBinding
    private val viewModel: DataClassUtilizationViewModel by activityViewModels()
    private var filterList: ArrayList<Order> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            filterList =
                bundle.getParcelableArrayList(EXTRA_ORDER)
                    ?: arrayListOf()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return setLayout(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        setObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): View {
        binding = BottomSheetResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initView() {
        viewModel.suggestPackagingStrategy(orderList = filterList)

        with(binding) {
            ivClose.setOnClickListener { dismiss() }
        }
    }

    private fun setObserve() {
        viewModel.productReview.observe(viewLifecycleOwner) { state ->
            binding.pbLoading.isVisible = state is States.Loading
            binding.rvResult.isVisible = state !is States.Loading && state !is States.Error

            state.handleStates(
                successBlock = { map ->
                    val productList: List<ProductQuantity> =
                        map.map { ProductQuantity("Order ID : ${it.key}", it.value) }
                    val orderDetailAdapter = OrderDetailAdapter()
                    orderDetailAdapter.setData(productList)

                    binding.rvResult.adapter = orderDetailAdapter
                    binding.rvResult.layoutManager = LinearLayoutManager(requireContext())
                },
                errorBlock = { },
                emptyBlock = { },
            )
        }
    }
}
