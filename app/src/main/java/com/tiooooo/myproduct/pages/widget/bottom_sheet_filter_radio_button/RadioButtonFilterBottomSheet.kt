package com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tiooooo.myproduct.databinding.BottomSheetFilterRadioButtonBinding
import com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.model.DropdownItem

class RadioButtonFilterBottomSheet : BottomSheetDialogFragment() {
    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_FILTER_LIST = "EXTRA_FILTER_LIST"
        private const val EXTRA_SELECTED_FILTER = "EXTRA_SELECTED_FILTER"

        fun newInstance(
            title: String,
            filterList: List<DropdownItem>,
            selectedFilter: DropdownItem? = null,
        ) = RadioButtonFilterBottomSheet().apply {
            val bundle = Bundle()
            bundle.putString(EXTRA_TITLE, title)
            bundle.putParcelableArrayList(
                EXTRA_FILTER_LIST,
                arrayListOf<DropdownItem>().apply { addAll(filterList) })
            if (selectedFilter != null) {
                bundle.putParcelable(EXTRA_SELECTED_FILTER, selectedFilter)
            }

            arguments = bundle
        }
    }

    private var title: String = ""
    private var filterList: ArrayList<DropdownItem> = arrayListOf()
    private var selectedFilter: DropdownItem? = null

    private lateinit var binding: BottomSheetFilterRadioButtonBinding
    var onFilterClick: ((filter: DropdownItem) -> Unit)? = null
    private val filterAdapter by lazy { RadioButtonFilterAdapter(filterList, selectedFilter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            title = bundle.getString(EXTRA_TITLE).orEmpty()
            filterList = bundle.getParcelableArrayList(EXTRA_FILTER_LIST) ?: arrayListOf()
            selectedFilter = bundle.getParcelable(EXTRA_SELECTED_FILTER)
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
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): View {
        binding = BottomSheetFilterRadioButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initView() {
        with(binding) {
            ivClose.setOnClickListener { dismiss() }
            tvTitle.text = title

            rvFilter.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = filterAdapter
            }

            filterAdapter.onFilterClick = {
                onFilterClick?.invoke(it)
                dismiss()
            }
        }
    }
}
