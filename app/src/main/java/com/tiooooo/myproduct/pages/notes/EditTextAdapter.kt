package com.tiooooo.myproduct.pages.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.tiooooo.myproduct.databinding.ItemCreateTodoBinding

class EditTextAdapter(private val editTextList: MutableList<String>) :
    RecyclerView.Adapter<EditTextAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCreateTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCreateTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.edtFreeText.setText(editTextList[position])

        holder.itemBinding.edtFreeText.doOnTextChanged { text, start, before, count ->
            editTextList[position] = text.toString()
        }
    }

    override fun getItemCount(): Int {
        return editTextList.size
    }
}

