package com.example.week1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import com.example.week1.databinding.DialogExitBinding


class ExitDialog(
    private val exit: ExitInterface
) : DialogFragment() {

    private var _binding: DialogExitBinding? = null
    private val binding
        get() = requireNotNull(_binding) { "ExitDialog's binding is null" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogExitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnExit.setOnClickListener{
            exit.exitBtnClick()
            dismiss()
        }
        binding.btnCancel.setOnClickListener{
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    interface ExitInterface {
        fun exitBtnClick()
    }

}