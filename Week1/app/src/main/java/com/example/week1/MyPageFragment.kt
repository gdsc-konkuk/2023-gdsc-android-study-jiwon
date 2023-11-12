package com.example.week1

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.week1.databinding.FragmentMyPageBinding
import com.example.week1.viewModel.NameViewModel
import com.example.week1.viewModel.ToDoViewModel

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "MyPageFragment's binding is null" }

    private val todoViewModel: ToDoViewModel by activityViewModels()
    private val nameViewModel: NameViewModel by activityViewModels()

//     MainActivity 코드 이전
    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getStringExtra("nickname").also {
                binding.nicknameTv.text = it
                nameViewModel.setName(it.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()

        binding.editMyProfileIv.setOnClickListener {
            val intent: Intent = Intent(requireContext(), EditActivity::class.java)
            intent.putExtra("nickname", binding.nicknameTv.text)
            intent.putExtra("email", binding.emailTv.text)
            startForResult.launch(intent)
        }
    }

    private fun setObserver() {

        val nameObserver = Observer<String> {
            binding.nicknameTv.text = it
        }
        nameViewModel.currentName.observe(viewLifecycleOwner, nameObserver)

        val countObserver = Observer<List<ToDoData>> {
            binding.tvTodoCount.text = "${todoViewModel.getDoneCount()} 개"
        }
        todoViewModel.currentToDoList.observe(viewLifecycleOwner, countObserver)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}