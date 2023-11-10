package com.example.week1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.week1.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nicknameTv.text = intent.getStringExtra("nickname")
        binding.emailTv.text = intent.getStringExtra("email")

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.storeBtn.setOnClickListener {
            if (binding.nicknameInputEt.text.isEmpty()) {
                Toast.makeText(this, "닉네임은 빈칸일 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            /*val resultIntent: Intent = Intent(this, MainActivity::class.java).apply {
                putExtra("nickname", binding.nicknameInputEt.text.toString())
            }
            setResult(Activity.RESULT_OK, resultIntent)*/
            finish()
        }
    }
}