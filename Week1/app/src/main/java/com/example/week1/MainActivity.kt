package com.example.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fragmentManager by lazy { supportFragmentManager }
    private val fragmentTransaction by lazy { fragmentManager.beginTransaction() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(fragmentTransaction, HomeFragment())

        binding.bnvBnv.setOnItemSelectedListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.item_home -> {
                    replaceFragment(fragmentTransaction, HomeFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.item_my_page -> {
                    replaceFragment(fragmentTransaction, MyPageFragment())
                    return@setOnItemSelectedListener true
                }

                else -> {
                    replaceFragment(fragmentTransaction, HomeFragment())
                    return@setOnItemSelectedListener true
                }
            }
        }

        binding.ibtnTodoPlus.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            replaceFragment(fragmentTransaction, CreateFragment())
        }
    }

    private fun replaceFragment(fragmentTransaction: FragmentTransaction, fragment: Fragment) {
        fragmentTransaction.replace(R.id.fl_content, fragment)
            .commit()
    }
}