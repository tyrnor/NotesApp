package com.example.todoapp.ui.view


import android.annotation.SuppressLint
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.ui.adapter.TaskAdapter
import com.example.todoapp.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initUIState()
    }

    private fun initRecyclerView() {
        taskAdapter = TaskAdapter()
        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
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

}
