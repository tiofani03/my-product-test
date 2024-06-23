package com.tiooooo.myproduct.pages.todo

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.tiooooo.myproduct.databinding.ItemCreateTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private val list: MutableList<Todo> = mutableListOf()

    fun setData(list: List<Todo>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    var onTodoTextChange: ((todo: Todo) -> Unit)? = null

    class ViewHolder(private val binding: ItemCreateTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val itemBinding = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCreateTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTodo = list[position]

//        holder.itemBinding.edtFreeText.removeTextChangedListener(holder.itemBinding.edtFreeText.tag as? TextWatcher)
//        val textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                onTodoTextChange?.invoke(itemTodo.copy(text = s.toString()))
//            }
//
//            override fun afterTextChanged(s: Editable?) {}
//        }
//        holder.itemBinding.edtFreeText.tag = textWatcher
//        holder.itemBinding.edtFreeText.addTextChangedListener(textWatcher)
//        holder.itemBinding.edtFreeText.setText(itemTodo.text)



        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onTodoTextChange?.invoke(itemTodo.copy(text = s.toString()))
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        holder.itemBinding.edtFreeText.removeTextChangedListener(textWatcher)
        holder.itemBinding.edtFreeText.addTextChangedListener(textWatcher)

        holder.itemBinding.edtFreeText.setText(itemTodo.text)
    }
}
