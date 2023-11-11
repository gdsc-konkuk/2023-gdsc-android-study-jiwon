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
        get() = requireNotNull(_binding) { "HomeFragment's binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = initData()
        binding.rvTodoList.adapter = ToDoAdapter(data)
        binding.rvTodoList.layoutManager = LinearLayoutManager(context)
    }

    private fun initData(): List<ToDoData> {
        return listOf(
            ToDoData(false, "안녕하세요", "0"),
            ToDoData(false, "안녕하세요1", "1"),
            ToDoData(false, "안녕하세요2", "2"),
            ToDoData(false, "안녕하세요3", "3"),
            ToDoData(true, "안녕하세요4", "0"),
            ToDoData(true, "안녕하세요5", "0"),
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}