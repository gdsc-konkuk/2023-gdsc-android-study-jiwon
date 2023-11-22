package com.example.week1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.week1.databinding.ActivityMainBinding
import com.example.week1.db.User
import com.example.week1.db.UserRoomDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val userDatabase = UserRoomDatabase.getDatabase(this)
        initDatabase(userDatabase)*/

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
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            replaceFragment(fragmentTransaction, CreateFragment())
        }
    }

    private fun initDatabase(userDatabase: UserRoomDatabase) {
        /*val userDao = userDatabase.userDao()
        val users = userDao.getAll()
        if (!users.isNullOrEmpty()) return
        userDao.insertUser(
            User(name = "박지원", imageUrl = "https://images.unsplash.com/photo-1691425266385-31dfd2603cca?ixid=M3w0MTIxNzd8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MDA0MjgzNDN8&ixlib=rb-4.0.3")
        )*/
    }

    private fun replaceFragment(fragmentTransaction: FragmentTransaction, fragment: Fragment) {
        fragmentTransaction.replace(R.id.fl_content, fragment)
            .commit()
    }
}