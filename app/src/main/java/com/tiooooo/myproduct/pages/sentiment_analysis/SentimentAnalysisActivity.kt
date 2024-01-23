package com.tiooooo.myproduct.pages.sentiment_analysis

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tiooooo.myproduct.databinding.ActivitySentimentAnalysisBinding
import com.tiooooo.myproduct.utils.handleStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SentimentAnalysisActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySentimentAnalysisBinding
    private val viewModel: SentimentAnalysisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySentimentAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        observeViewModel()
    }

    private fun initView() {
        viewModel.analyzeCustomerFeedback()
        binding.edtFreeText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    binding.btnCheck.isEnabled = s.toString().isNotEmpty()
                    viewModel.textSentimentAnalysis.value = s.toString()
                    binding.tvResult.isVisible = s.toString().isNotEmpty()
                }
            }
        })
        binding.btnCheck.setOnClickListener {
            viewModel.textSentimentAnalysis.value = binding.edtFreeText.text.toString()
        }
    }

    private fun observeViewModel() {
        viewModel.result.observe(this) { state ->
            state.handleStates(
                loadingBlock = {
                    binding.pbLoading.isVisible = true
                    binding.tvResult.isVisible = false
                },
                successBlock = {
                    binding.tvResult.isVisible = it.isNotEmpty()
                    binding.pbLoading.isVisible = false
                    binding.tvResult.text = it
                },
                errorBlock = { },
                emptyBlock = { },
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
