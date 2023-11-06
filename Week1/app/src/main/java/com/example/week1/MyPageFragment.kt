package com.example.week1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.week1.databinding.FragmentHomeBinding
import com.example.week1.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding
        get() = _binding!!

    /* MainActivity 코드 이전
    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            binding.nicknameTv.text = result.data?.getStringExtra("nickname")
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.editMyProfileIv.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), EditActivity::class.java)
            intent.putExtra("nickname", binding.nicknameTv.text)
            intent.putExtra("email", binding.emailTv.text)
//            startForResult.launch(intent)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}