package com.example.baitapevent

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.baitapevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents() {
        // 1. Lắng nghe sự kiện gõ phím EditText
        binding.etFullName.doAfterTextChanged { text ->
            if (!text.isNullOrEmpty()) {
                Toast.makeText(this, "Đang nhập tên: $text", Toast.LENGTH_SHORT).show()
            }
        }

        // 2. Lắng nghe sự kiện RadioGroup giới tính
        binding.rgGender.setOnCheckedChangeListener { _, checkedId ->
            val gender = when (checkedId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                else -> ""
            }
            Toast.makeText(this, "Đã chọn giới tính: $gender", Toast.LENGTH_SHORT).show()
        }

        // 3. Lắng nghe sự kiện CheckBox Mạng xã hội
        binding.cbFacebook.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Đã chọn Facebook" else "Đã bỏ chọn Facebook"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }
        binding.cbInstagram.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Đã chọn Instagram" else "Đã bỏ chọn Instagram"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }
        binding.cbTiktok.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Đã chọn TikTok" else "Đã bỏ chọn TikTok"
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        // 4. Xử lý nút Đăng ký -> Chuyển màn hình chi tiết
        binding.btnSubmit.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            val gender = when (binding.rgGender.checkedRadioButtonId) {
                R.id.rbMale -> "Nam"
                R.id.rbFemale -> "Nữ"
                else -> "Chưa chọn"
            }

            val socials = mutableListOf<String>()
            if (binding.cbFacebook.isChecked) socials.add("Facebook")
            if (binding.cbInstagram.isChecked) socials.add("Instagram")
            if (binding.cbTiktok.isChecked) socials.add("TikTok")

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_NAME", name)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_GENDER", gender)
                putExtra("EXTRA_SOCIALS", socials.joinToString(", "))
            }
            startActivity(intent)
        }
    }
}