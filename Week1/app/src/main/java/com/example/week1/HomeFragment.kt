package com.example.week1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week1.utils.TAG
import com.example.week1.databinding.FragmentHomeBinding
import com.example.week1.viewModel.ToDoViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "HomeFragment's binding is null" }

    private val todoViewModel: ToDoViewModel by activityViewModels()

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
        binding.rvTodoList.adapter = ToDoAdapter(todoViewModel.currentToDoList.value!!)
        binding.rvTodoList.layoutManager = LinearLayoutManager(context)

        val todoObserver = Observer<List<ToDoData>> {
            binding.rvTodoList.adapter = ToDoAdapter(todoViewModel.currentToDoList.value!!)
        }
        todoViewModel.currentToDoList.observe(viewLifecycleOwner, todoObserver)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        Log.d(TAG, "HomeFragment - onDestroyView() called")
    }

}