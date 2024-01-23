package com.tiooooo.myproduct.pages.data_class_utilization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tiooooo.myproduct.databinding.ItemOrderBinding
import com.tiooooo.myproduct.model.Order

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val list: MutableList<Order> = mutableListOf()
    fun setData(list: List<Order>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    class ViewHolder(
        private val binding: ItemOrderBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(order: Order) {
            binding.apply {
                tvOrderId.text = "#${order.orderId}"
                tvRecipientName.text = order.recipientName
                tvAddress.text = order.address

                val productList: List<ProductQuantity> =
                    order.quantity.map { ProductQuantity(it.key, it.value.toString()) }
                val orderDetailAdapter = OrderDetailAdapter()
                orderDetailAdapter.setData(productList)

                rvProduct.adapter = orderDetailAdapter
                rvProduct.layoutManager = LinearLayoutManager(itemView.context)
            }
        }
    }
}
