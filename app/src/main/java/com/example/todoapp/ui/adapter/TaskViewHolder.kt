package com.example.todoapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.domain.entities.AnimationState
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.entities.IsIconsVisible.*
import com.example.todoapp.ui.core.listeners.OnSwipeTouchListener

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var appearAnimation: Animation
    private lateinit var disappearAnimation: Animation
    private lateinit var disappearAnimation2: Animation
    private var animationState: AnimationState

    init {
        binding.buttons.visibility = View.GONE
        animationState = AnimationState.Ended
    }

    @SuppressLint("ClickableViewAccessibility")
    fun bind(
        task: Task,
        taskActions: TaskAdapter.TaskActions?,
        updateIconVisibility: TaskAdapter.UpdateIconVisibility?,
        taskItemClickListener: TaskAdapter.TaskItemClickListener?,
        isExpanded: Boolean,
        onStateChange: (Int) -> Unit,
    ) {
        setupAnimations(task, onStateChange, updateIconVisibility)
        when(task.isIconsVisible){
            Hidden -> binding.buttons.visibility = View.GONE
            Visible -> binding.buttons.visibility = View.VISIBLE
            PrevIcon -> binding.buttons.apply {
                startAnimation(disappearAnimation2)
                visibility = View.GONE
                updateIconVisibility?.updateIconVisibility(task, Hidden)
            }

            else -> {
                binding.buttons.visibility = View.GONE
            }
        }
        binding.textViewTitle.text = task.title
        binding.textViewDescription.text = task.description.ifBlank { "No more text" }

        binding.ivDelete.setOnClickListener {
            taskActions?.onDeleteTask(task)
            onStateChange(-1)
        }
        binding.texts.setOnTouchListener(object :
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
        })
        if (animationState == AnimationState.Ended){
            binding.texts.setOnClickListener {
                if (task.isIconsVisible == Hidden || task.isIconsVisible == PrevIcon){
                    Log.i("TEST", "onClick: TEST")
                    taskItemClickListener?.onItemClick(task)
                }
                if (isExpanded) {
                    binding.buttons.apply {
                        startAnimation(disappearAnimation)
                        updateIconVisibility?.updateIconVisibility(task, Hidden)
                        visibility = View.GONE
                    }
                }
            }
        }


    }
    private fun setupAnimations(
        task: Task,
        onStateChange: (Int) -> Unit,
        updateIconVisibility: TaskAdapter.UpdateIconVisibility?
    ) {
        appearAnimation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {animationState = AnimationState.OnCourse}
                    override fun onAnimationEnd(animation: Animation) {
                        onStateChange(absoluteAdapterPosition)
                        updateIconVisibility?.updateIconVisibility(task, Visible)
                        animationState = AnimationState.Ended
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }

        disappearAnimation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {animationState = AnimationState.OnCourse}
                    override fun onAnimationEnd(animation: Animation) {
                        onStateChange(-1)
                        updateIconVisibility?.updateIconVisibility(task, Hidden)
                        animationState = AnimationState.Ended
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
        disappearAnimation2 = AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left)

    }

}


