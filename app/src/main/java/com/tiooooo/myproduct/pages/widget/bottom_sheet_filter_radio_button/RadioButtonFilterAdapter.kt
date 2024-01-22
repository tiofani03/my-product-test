package com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiooooo.myproduct.databinding.ItemFilterRadioButtonBinding
import com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.model.DropdownItem

class RadioButtonFilterAdapter(
    private val filterList: List<DropdownItem>,
    selectedActualFilter: DropdownItem? = null,
) : RecyclerView.Adapter<RadioButtonFilterAdapter.ViewHolder>() {

    private var selectedFilter: DropdownItem? = selectedActualFilter
    var onFilterClick: ((filter: DropdownItem) -> Unit)? = null

    class ViewHolder(viewBinding: ItemFilterRadioButtonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        val filter = viewBinding.radioFilter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFilterRadioButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = filterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filter.apply {
            text = filterList[position].title
            isChecked = filterList[position].id == selectedFilter?.id
            setOnClickListener {
                selectedFilter = filterList[position]
                onFilterClick?.invoke(filterList[position])
            }
        }
    }
}
