package com.example.week1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week1.databinding.FragmentHomeBinding
import com.example.week1.db.Todo
import com.example.week1.db.TodoViewModel
import com.example.week1.db.TodoViewModelFactory
import com.example.week1.db.User
import com.example.week1.utils.TAG
import com.example.week1.viewModel.ToDoViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "HomeFragment's binding is null" }

    private val todoViewModel: ToDoViewModel by activityViewModels()
//        private lateinit var todoViewModel: TodoViewModel

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
//        todoViewModel = ViewModelProvider(this, TodoViewModelFactory(
//            (requireActivity().application as Application).database
//                .todoDao()
//        )
//        )[TodoViewModel::class.java]
//
//        val todoObserver = Observer<List<Todo>> {
//            if (it != null) {
//                Log.e(TAG, "MyPageFragment - setObserver()\nit = ${it}")
//                binding.rvTodoList.adapter = ToDoAdapter(it)
//                binding.rvTodoList.layoutManager = LinearLayoutManager(context)
//            } else {
//                // users가 null인 경우 처리 코드
//                Log.e(TAG, "users is null")
//            }
//        }
//        todoViewModel.currentTodoList.observe(viewLifecycleOwner, todoObserver)

        binding.rvTodoList.adapter = ToDoAdapter(todoViewModel.currentToDoList.value!!)
        binding.rvTodoList.layoutManager = LinearLayoutManager(context)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}