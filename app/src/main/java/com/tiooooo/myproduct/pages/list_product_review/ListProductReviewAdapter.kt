package com.tiooooo.myproduct.pages.list_product_review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tiooooo.myproduct.databinding.ItemReviewBinding
import com.tiooooo.myproduct.model.ProductReview

class ListProductReviewAdapter :
    ListAdapter<ProductReview, ListProductReviewAdapter.ViewHolder>(ProductReviewDiffCallback()) {
    private class ProductReviewDiffCallback : DiffUtil.ItemCallback<ProductReview>() {
        override fun areItemsTheSame(oldItem: ProductReview, newItem: ProductReview): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductReview, newItem: ProductReview): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(
        private val binding: ItemReviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(productReview: ProductReview?) {
            productReview?.let {
                binding.apply {
                    rbProduct.rating = it.rating.toFloat()
                    tvProductReview.text = it.review
                    tvProductName.text = it.name
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(currentList[position])
    }
}
