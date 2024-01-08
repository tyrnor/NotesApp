package com.example.todoapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddEditTaskBinding
import com.example.todoapp.domain.entities.IsIconsVisible
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.ui.viewmodel.AddEditTaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditTaskFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: AddEditTaskViewModel by activityViewModels()
    private var _binding: FragmentAddEditTaskBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddEditTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initNavigation()
        initListeners()
    }

    private fun initNavigation() {
        navController = requireActivity().findNavController(R.id.fragmentContainerView)
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            navController.popBackStack(R.id.taskListFragment, false)
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
            navController.popBackStack(R.id.taskListFragment, false)
        }
    }

}