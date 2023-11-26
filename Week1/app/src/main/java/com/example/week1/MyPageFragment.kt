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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import com.bumptech.glide.Glide
import com.example.week1.databinding.FragmentMyPageBinding
import com.example.week1.db.UserViewModel
import com.example.week1.db.UserViewModelFactory
import com.example.week1.db.User
import com.example.week1.db.UserDao
import com.example.week1.db.UserRoomDatabase
import com.example.week1.utils.TAG
import com.example.week1.viewModel.ToDoViewModel
import kotlinx.coroutines.launch

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "MyPageFragment's binding is null" }

//    private val userDao: UserDao by lazy { UserRoomDatabase.getDatabase(requireContext()).userDao() }
    private val todoViewModel: ToDoViewModel by activityViewModels()
//    private val nameViewModel: NameViewModel by activityViewModels()
//    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var userViewModel: UserViewModel
    /*private val userViewModel: UserViewModel by activityViewModels() {
        UserViewModelFactory(
            (activity?.application as Application).database
                .userDao()
        )
    }*/
//    private lateinit var users: LiveData<List<User>>

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            /*result.data?.getStringExtra("nickname").also {
                binding.nicknameTv.text = it
//                nameViewModel.setName(it.toString())
            }
            result.data?.getStringExtra("imageUrl").also {
                Glide.with(requireContext())
                    .load(it)
                    .centerCrop()
                    .into(binding.profileImageIv)
//                nameViewModel.setImage(it.toString())
            }*/

            val name = requireNotNull(result.data?.getStringExtra("nickname"))
            val imageUrl = requireNotNull(result.data?.getStringExtra("imageUrl"))
            val user = User(uid= requireNotNull(userViewModel.currentUser.value).first().uid, name = name, imageUrl = imageUrl)
//            val user = User(uid= requireNotNull(users.value).first().uid, name = name, imageUrl = imageUrl)
            if (userViewModel.isEntryValid(name, imageUrl)) {
                userViewModel.updateUser(user)
            }
            /*userViewModel.setUser(user)
            userDao.updateUser(user)*/
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
//        users = userViewModel.getAllUser()
        Log.d(TAG, "MyPageFragment - users = {${userViewModel.currentUser.value}}")
//        Log.d(TAG, "MyPageFragment - users = {${users.value}}")

        setObserver()

//        setProfile(requireNotNull(users.value) { "MyPageFragment's users.value is null" })
        setProfile(requireNotNull(userViewModel.currentUser.value) { "MyPageFragment's users.value is null" })

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

        /*val nameObserver = Observer<String> {
            binding.nicknameTv.text = it
        }
        nameViewModel.currentName.observe(viewLifecycleOwner, nameObserver)

        val imageObserver = Observer<String> {
            Glide.with(requireContext())
                .load(it)
                .centerCrop()
                .into(binding.profileImageIv)
        }
        nameViewModel.currentImage.observe(viewLifecycleOwner, imageObserver)*/

        /*val userObserver = Observer<User> {
            binding.nicknameTv.text = it.name
            Glide.with(requireContext())
                .load(it.imageUrl)
                .centerCrop()
                .into(binding.profileImageIv)
            userDao.updateUser(it)
        }
        userViewModel.currentUser.observe(viewLifecycleOwner, userObserver)*/

        val usersObserver = Observer<List<User>> {
            setProfile(it)
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