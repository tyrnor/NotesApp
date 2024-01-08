package com.example.todoapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.entities.IsIconsVisible.*
import com.example.todoapp.ui.core.listeners.OnSwipeTouchListener

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var appearAnimation: Animation
    private lateinit var disappearAnimation: Animation
    private lateinit var disappearAnimation2: Animation

    init {
        binding.buttons.visibility = View.GONE
    }

    @SuppressLint("ClickableViewAccessibility")
    fun bind(
        task: Task,
        taskActions: TaskAdapter.TaskActions?,
        updateIconVisibility: TaskAdapter.UpdateIconVisibility?,
        isExpanded: Boolean,
        onStateChange: (Int) -> Unit,
    ) {
        Log.i("Test", "bind: ${task.title} ${task.isIconsVisible}")

        setupAnimations(task, onStateChange, updateIconVisibility)


        when(task.isIconsVisible){
            Hidden -> binding.buttons.visibility = View.GONE
            Visible -> binding.buttons.visibility = View.VISIBLE
            PrevIcon -> binding.buttons.apply {
                Log.i("TEST", "bind: Start PrevIcon anim ${task.title}")
                startAnimation(disappearAnimation2)
                visibility = View.GONE
                updateIconVisibility?.updateIconVisibility(task, Hidden)
            }
        }


        binding.ivDelete.setOnClickListener {
            taskActions?.onDeleteTask(task)
            onStateChange(-1)
        }

        binding.textViewTitle.text = task.title
        binding.textViewTitle.setOnTouchListener(object :
            OnSwipeTouchListener(binding.root.context) {

            override fun onSwipeLeft() {
                if (!isExpanded) {
                    binding.buttons.apply {
                        visibility = View.VISIBLE
                        startAnimation(appearAnimation)
                        updateIconVisibility?.updateIconVisibility(task, Visible)
                    }
                }
            }
            override fun onSwipeRight() {
                if (isExpanded) {
                    binding.buttons.apply {
                        startAnimation(disappearAnimation)
                        updateIconVisibility?.updateIconVisibility(task, Hidden)
                        visibility = View.GONE
                    }
                }
            }
            override fun onClick(v: View) {
                if (isExpanded) {
                    binding.buttons.apply {
                        startAnimation(disappearAnimation)
                        updateIconVisibility?.updateIconVisibility(task, Hidden)
                        visibility = View.GONE
                    }
                }
            }
        })

    }

    private fun setupAnimations(
        task: Task,
        onStateChange: (Int) -> Unit,
        updateIconVisibility: TaskAdapter.UpdateIconVisibility?
    ) {
        appearAnimation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        onStateChange(absoluteAdapterPosition)
                        updateIconVisibility?.updateIconVisibility(task, Visible)
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }

        disappearAnimation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        onStateChange(-1)
                        updateIconVisibility?.updateIconVisibility(task, Hidden)
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
        disappearAnimation2 =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                    }
                    override fun onAnimationEnd(animation: Animation) {

                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
    }

}


