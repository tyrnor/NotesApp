package com.example.todoapp.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditTaskBinding
import com.example.todoapp.ui.viewmodel.EditTaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditTaskFragment : Fragment() {


    private val viewModel: EditTaskViewModel by activityViewModels()
    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    private val args: EditTaskFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTask(args.idTask)
        initUI()
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            viewModel.task.collect{
                binding.etTitle.text = editableText(it.title)
                binding.etDescription.text = editableText(it.description)
            }
        }
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
            lifecycleScope.launch {
                viewModel.task.collect{
                    it.title = binding.etTitle.text.toString()
                    it.description = binding.etDescription.text.toString()
                    viewModel.updateTask(it)
                }
            }
            findNavController().popBackStack(R.id.taskListFragment, false)
        }
    }

    private fun editableText(text: String): Editable{
        return Editable.Factory.getInstance().newEditable(text)
    }
}