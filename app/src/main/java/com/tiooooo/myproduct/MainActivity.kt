package com.tiooooo.myproduct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiooooo.myproduct.databinding.ActivityMainBinding
import com.tiooooo.myproduct.pages.list_product_review.ListProductReviewActivity
import com.tiooooo.myproduct.pages.sentiment_analysis.SentimentAnalysisActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnProblem1.setOnClickListener {

            }

            btnProblem2.setOnClickListener {
                startActivity(Intent(this@MainActivity, SentimentAnalysisActivity::class.java))
            }

            btnProblem3.setOnClickListener {
                startActivity(Intent(this@MainActivity, ListProductReviewActivity::class.java))
            }
        }
    }
}
