package com.example.week1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.week1.databinding.ActivityEditBinding
import com.example.week1.network.ResultPhotosRamdom
import com.example.week1.network.RetrofitUtil
import com.example.week1.network.UnsplashService.Companion.TAG
import retrofit2.Call
import retrofit2.Response

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url: String? = null

        getIntentData(intent)

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.storeBtn.setOnClickListener {
            if (binding.nicknameInputEt.text.isEmpty()) {
                Toast.makeText(this, "닉네임은 빈칸일 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val imageUrl = requireNotNull(url) { "EditActivity's imageUrl is null" }

            val resultIntent: Intent = Intent(this, MainActivity::class.java).apply {
                putExtra("nickname", binding.nicknameInputEt.text.toString())
                putExtra("imageUrl", imageUrl)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        binding.nicknameInputEt.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.nicknameInputEt.setText("")
            }
        }

        binding.profileImageInputBtn.setOnClickListener {
            RetrofitUtil.unsplashService.requestRandomPhoto()
                .enqueue(object : retrofit2.Callback<ResultPhotosRamdom> {
                    override fun onResponse(
                        call: Call<ResultPhotosRamdom>,
                        response: Response<ResultPhotosRamdom>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(TAG, "EditActivity - onResponse() called: 성공")
                            val body =
                                requireNotNull(response.body()) { "Retrofit-requestRandomPhoto()'s result is null" }
                            url = body.urls.url

                            Glide.with(this@EditActivity)
                                .load(url)
                                .centerCrop()
                                .into(binding.profileImageIv)

                            return
                        }
                        Log.d(TAG, "EditActivity - onResponse() called: 실패")
                    }

                    override fun onFailure(call: Call<ResultPhotosRamdom>, t: Throwable) {
                        Log.d(TAG, "EditActivity - onFailure() called")
                    }

                })
        }

    }

    private fun getIntentData(intent: Intent?) {

        requireNotNull(intent) { "EditActivity's intent is null" }.also {
            it.getStringExtra("nickname").also { name ->
                binding.nicknameTv.text = name
                binding.nicknameInputEt.setText(name)
            }

            it.getStringExtra("email").also { email ->
                binding.emailTv.text = email
            }

            it.getStringExtra("imageUrl").also { imageUrl ->
                Glide.with(this)
                    .load(imageUrl)
                    .centerCrop()
                    .into(binding.profileImageIv)
            }
        }
    }
}