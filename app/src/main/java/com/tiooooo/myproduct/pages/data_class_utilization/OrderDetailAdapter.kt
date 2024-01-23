package com.tiooooo.myproduct.pages.data_class_utilization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiooooo.myproduct.databinding.ItemOrderDetailBinding

data class ProductQuantity(val productName: String, val quantity: String)

class OrderDetailAdapter : RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {
    private val list: MutableList<ProductQuantity> = mutableListOf()
    fun setData(list: List<ProductQuantity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemOrderDetailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(productQuantity: ProductQuantity) {
            with(binding) {
                tvProductCount.text = productQuantity.quantity
                tvProductName.text = productQuantity.productName
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }
}
