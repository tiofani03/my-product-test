package com.tiooooo.myproduct.pages.todo

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiooooo.myproduct.databinding.ActivityTodoBinding

class TodoActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityTodoBinding
    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter()

        binding.btnAdd.setOnClickListener {
            viewModel.addTodo()
        }

        binding.rvTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@TodoActivity)
        }

        viewModel.todoListLiveData.observe(this) { todoList ->
            Log.d("TODOA", "Updated todo list: $todoList")
            todoAdapter.setData(todoList)
        }

        todoAdapter.onTodoTextChange = { updatedTodo ->
            viewModel.updateTodoData(updatedTodo)
        }
    }
}
