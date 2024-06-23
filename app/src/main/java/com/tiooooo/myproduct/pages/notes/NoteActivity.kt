package com.tiooooo.myproduct.pages.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiooooo.myproduct.R
import com.tiooooo.myproduct.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private lateinit var editTextAdapter: EditTextAdapter
    private val editTextList: MutableList<String> = mutableListOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            editTextAdapter = EditTextAdapter(editTextList)
            recyclerView.adapter = editTextAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@NoteActivity)

            buttonAdd.setOnClickListener {
                editTextList.add("")
                editTextAdapter.notifyItemInserted(editTextList.size - 1)
            }

            buttonCollect.setOnClickListener {
                collectData()
            }
        }
    }

    private fun collectData() {
        val collectedData = StringBuilder()
        for (i in 0 until editTextList.size) {
            collectedData.append("Item ${i + 1}: ${editTextList[i]}\n")
        }
        Toast.makeText(this, "$collectedData", Toast.LENGTH_SHORT).show()
    }
}
