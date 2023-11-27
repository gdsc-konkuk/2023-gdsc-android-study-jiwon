package com.example.week1

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.week1.databinding.FragmentMyPageBinding
import com.example.week1.db.TodoViewModel
import com.example.week1.db.TodoViewModelFactory
import com.example.week1.db.UserViewModel
import com.example.week1.db.UserViewModelFactory
import com.example.week1.db.User
import com.example.week1.utils.TAG
import com.example.week1.viewModel.ToDoViewModel

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "MyPageFragment's binding is null" }
    private val todoViewModel: ToDoViewModel by activityViewModels()
    private lateinit var userViewModel: UserViewModel
    /*private val userViewModel: UserViewModel by activityViewModels() {
        UserViewModelFactory(
            (activity?.application as Application).database
                .userDao()
        )
    }*/
//    private lateinit var todoViewModel: TodoViewModel

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val name = requireNotNull(result.data?.getStringExtra("nickname"))
            val imageUrl = requireNotNull(result.data?.getStringExtra("imageUrl"))
            val user = User(uid= requireNotNull(userViewModel.currentUser.value).first().uid, name = name, imageUrl = imageUrl)

            if (userViewModel.isEntryValid(name, imageUrl)) {
                userViewModel.updateUser(user)
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
        userViewModel = ViewModelProvider(this, UserViewModelFactory(
            (requireActivity().application as Application).database
                .userDao()
        ))[UserViewModel::class.java]
//        todoViewModel = ViewModelProvider(this, TodoViewModelFactory(
//            (requireActivity().application as Application).database
//                .todoDao()
//        ))[TodoViewModel::class.java]

        setObserver()

        binding.editMyProfileIv.setOnClickListener {
            val intent: Intent = Intent(requireContext(), EditActivity::class.java)
            intent.putExtra("nickname", binding.nicknameTv.text)
            intent.putExtra("email", binding.emailTv.text)
            startForResult.launch(intent)
        }
    }

    private fun setProfile(users: List<User>) {
        binding.nicknameTv.text = users.first().name
        Glide.with(requireActivity())
            .load(users.first().imageUrl)
            .centerCrop()
            .into(binding.profileImageIv)
    }

    private fun setObserver() {
        val usersObserver = Observer<List<User>> {
            if (it != null) {
                Log.e(TAG, "MyPageFragment - setObserver()\nit = ${it}")
                setProfile(it)
            } else {
                // users가 null인 경우 처리 코드
                Log.e(TAG, "users is null")
            }
        }
        userViewModel.currentUser.observe(viewLifecycleOwner, usersObserver)

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