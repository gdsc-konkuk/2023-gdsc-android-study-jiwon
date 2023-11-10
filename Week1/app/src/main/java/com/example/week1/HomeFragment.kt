package com.example.week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val data = initData()
        binding.rvTodoList.adapter = ToDoAdapter(data)
        binding.rvTodoList.layoutManager = LinearLayoutManager(context)
    }

    private fun initData(): ArrayList<ToDoData> {
        return arrayListOf(
            ToDoData(false, "안녕하세요", "0"),
            ToDoData(false, "안녕하세요1", "1"),
            ToDoData(false, "안녕하세요2", "2"),
            ToDoData(false, "안녕하세요3", "3"),
            ToDoData(true, "안녕하세요4", "0"),
            ToDoData(true, "안녕하세요5", "0"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}