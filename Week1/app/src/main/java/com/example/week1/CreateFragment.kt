package com.example.week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.week1.databinding.FragmentCreateBinding
import com.example.week1.databinding.FragmentHomeBinding
import com.example.week1.viewModel.ToDoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCreateBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "CreateFragment's binding is null" }

    private val toDoViewModel: ToDoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStore.setOnClickListener {
            if(validateTodo(binding.etTodoContent.toString())) {
                val newTodo = ToDoData(false, binding.etTodoContent.text.toString(), "0")
                toDoViewModel.addNewTodo(newTodo)
//                내려가기
                this.dismiss()

            }
        }
    }

    private fun validateTodo(content: String): Boolean {
        if(content.isEmpty()) {
            return false
        }
        return true
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val TAG = "CreateFragment - BottomSheet"
    }
}