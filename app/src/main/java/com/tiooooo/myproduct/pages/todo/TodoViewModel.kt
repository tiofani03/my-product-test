package com.tiooooo.myproduct.pages.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val mainTodoList = mutableListOf(Todo())
    private val _todoListLiveData = MutableLiveData<List<Todo>>()
    val todoListLiveData = _todoListLiveData

    init {
        _todoListLiveData.value = mainTodoList.toList()
    }

    fun addTodo() {
        val newTodo = Todo()
        mainTodoList.add(newTodo)
        _todoListLiveData.value = mainTodoList.toList()
    }

    fun updateTodoData(todo: Todo) {
        val index = mainTodoList.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            mainTodoList[index] = todo
            _todoListLiveData.value = mainTodoList.toList()
        }
    }

    fun updateNotes(todo: Todo, notes: String) {
        val todoToUpdate = mainTodoList.find { it.id == todo.id }
        todoToUpdate?.let {
            it.text = notes
            _todoListLiveData.value = mainTodoList.toList()
        }
    }
}

