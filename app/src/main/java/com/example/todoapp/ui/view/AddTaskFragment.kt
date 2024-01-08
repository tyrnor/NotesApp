package com.example.todoapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.ui.viewmodel.AddTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private val viewModel: AddTaskViewModel by activityViewModels()
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListeners()

    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack(R.id.taskListFragment, false)
        }
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val isTitleNotEmpty = !p0.isNullOrBlank()
                binding.tvConfirm.isEnabled = isTitleNotEmpty
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
        binding.tvConfirm.setOnClickListener {
            viewModel.addTask(
                Task(
                    title = binding.etTitle.text.toString(),
                    description = binding.etDescription.text.toString(),
                    creationDate = null,
                    isIconsVisible = IsIconsVisible.Hidden
                )
            )
            findNavController().popBackStack(R.id.taskListFragment, false)
        }
    }

}