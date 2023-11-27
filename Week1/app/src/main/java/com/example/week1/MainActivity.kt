package com.example.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ExitDialog.ExitInterface {

    private lateinit var binding: ActivityMainBinding

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val dialog = ExitDialog(this@MainActivity)
            dialog.isCancelable = false
            dialog.show(this@MainActivity.supportFragmentManager, "ConfirmDialog")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

        replaceFragment(supportFragmentManager.beginTransaction(), HomeFragment())

        binding.bnvBnv.setOnItemSelectedListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
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
            /*val fragmentTransaction = supportFragmentManager.beginTransaction()
            replaceFragment(fragmentTransaction, CreateFragment())*/
            val createTodoBottomSheet = CreateFragment()
            createTodoBottomSheet.show(supportFragmentManager, CreateFragment.TAG)
        }
    }

    private fun replaceFragment(fragmentTransaction: FragmentTransaction, fragment: Fragment) {
        fragmentTransaction.replace(R.id.fl_content, fragment)
            .commit()
    }

    override fun exitBtnClick() {
        finish()
    }
}