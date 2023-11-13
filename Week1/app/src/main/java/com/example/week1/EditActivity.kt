package com.example.week1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.week1.databinding.ActivityEditBinding
import com.example.week1.viewModel.NameViewModel

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("nickname").also { name ->
            binding.nicknameTv.text = name
            binding.nicknameInputEt.setText(name)
        }

        binding.emailTv.text = intent.getStringExtra("email")

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.storeBtn.setOnClickListener {
            if (binding.nicknameInputEt.text.isEmpty()) {
                Toast.makeText(this, "닉네임은 빈칸일 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val resultIntent: Intent = Intent(this, MainActivity::class.java).apply {
                putExtra("nickname", binding.nicknameInputEt.text.toString())
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

         binding.nicknameInputEt.setOnFocusChangeListener { v, hasFocus ->
             if(hasFocus) {
                 binding.nicknameInputEt.setText("")
             }
         }

    }
}