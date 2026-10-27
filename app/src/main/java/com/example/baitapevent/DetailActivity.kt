package com.example.baitapevent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baitapevent.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val gender = intent.getStringExtra("EXTRA_GENDER") ?: ""
        val socials = intent.getStringExtra("EXTRA_SOCIALS") ?: "Không có"

        val resultText = """
            - Họ và tên: $name
            - Email: $email
            - Giới tính: $gender
            - Mạng xã hội: $socials
        """.trimIndent()

        binding.tvResultInfo.text = resultText
    }
}