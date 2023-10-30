package com.example.week1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editMyProfileIv.setOnClickListener {
            val intent: Intent = Intent(this, EditActivity::class.java)
            intent.putExtra("nickname", binding.nicknameTv.text)
            intent.putExtra("email", binding.emailTv.text)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            binding.nicknameTv.text = data?.getStringExtra("nickname")
        }
    }
}