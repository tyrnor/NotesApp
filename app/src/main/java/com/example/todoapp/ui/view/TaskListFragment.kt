package com.example.todoapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTaskListBinding
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.ui.adapter.TaskAdapter
import com.example.todoapp.ui.viewmodel.TaskListViewModel
import kotlinx.coroutines.launch

class TaskListFragment : Fragment(), TaskAdapter.TaskActions {

    private val viewModel: TaskListViewModel by activityViewModels()

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTaskListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            viewModel.testTasks.collect { tasks ->
                if (tasks.isEmpty()) {
                    binding.tvEmptyList.visibility = View.VISIBLE
                    binding.rvTasks.visibility = View.GONE
                } else {
                    binding.tvEmptyList.visibility = View.GONE
                    binding.rvTasks.visibility = View.VISIBLE
                    taskAdapter.submitList(tasks)
                }
            }
        }
    }

    private fun initRecyclerView() {
        taskAdapter = TaskAdapter().apply{
            taskActions = this@TaskListFragment
        }

        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
    }

    override fun onDeleteTask(task: Task) {
        viewModel.deleteTask(task)
    }



}