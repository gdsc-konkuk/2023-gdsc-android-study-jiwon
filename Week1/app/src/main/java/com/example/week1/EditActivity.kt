package com.example.week1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.week1.databinding.ActivityEditBinding
import com.example.week1.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nicknameTv.text = intent.getStringExtra("nickname")
        binding.emailTv.text = intent.getStringExtra("email")

        binding.backIv.setOnClickListener{
            finish()
        }

        binding.storeBtn.setOnClickListener {
            if(binding.nicknameInputEt.text.isEmpty()) {
                Toast.makeText(this, "닉네임은 빈칸일 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent: Intent = Intent().putExtra("nickname", binding.nicknameInputEt.text)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}