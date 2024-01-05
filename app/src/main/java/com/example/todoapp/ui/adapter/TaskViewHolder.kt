package com.example.todoapp.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.domain.entities.isIconsVisible
import com.example.todoapp.domain.entities.isIconsVisible.*
import com.example.todoapp.ui.core.listeners.OnSwipeTouchListener

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var appearAnimation: Animation
    private lateinit var disappearAnimation: Animation
    private lateinit var disappearAnimation2: Animation

    @SuppressLint("ClickableViewAccessibility")
    fun bind(
        task: Task,
        taskActions: TaskAdapter.TaskActions?,
        isExpanded: Boolean,
        onStateChange: (isIconsVisible, Int) -> Unit,
    ) {
        setupAnimations(task, onStateChange)

        when(task.isIconsVisible){
            Hidden -> binding.buttons.visibility = View.GONE
            Visible -> binding.buttons.visibility = View.VISIBLE
            PrevIcon -> binding.buttons.apply {
                startAnimation(disappearAnimation2)
                visibility = View.GONE
            }
        }


        binding.ivDelete.setOnClickListener {
            taskActions?.onDeleteTask(task)
            onStateChange(Hidden, -1)
        }

        binding.textViewTitle.text = task.title
        binding.textViewTitle.setOnTouchListener(object :
            OnSwipeTouchListener(binding.root.context) {

            override fun onSwipeLeft() {
                if (!isExpanded) {
                    binding.buttons.apply {
                        startAnimation(appearAnimation)
                        visibility = View.VISIBLE
                    }
                }
            }
            override fun onSwipeRight() {
                if (isExpanded) {
                    binding.buttons.apply {
                        startAnimation(disappearAnimation)
                        visibility = View.GONE
                    }
                }
            }
            override fun onClick(v: View) {
                if (isExpanded) {
                    binding.buttons.apply {
                        startAnimation(disappearAnimation)
                        visibility = View.GONE
                    }
                }
            }
        })

    }

    private fun setupAnimations(
        task: Task,
        onStateChange: (isIconsVisible, Int) -> Unit
    ) {
        appearAnimation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        onStateChange(Visible, absoluteAdapterPosition)
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }

        disappearAnimation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {}
                    override fun onAnimationEnd(animation: Animation) {
                        onStateChange(Hidden, -1)
                    }
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
        disappearAnimation2 =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {task.isIconsVisible = Hidden}
                    override fun onAnimationEnd(animation: Animation) {}
                    override fun onAnimationRepeat(animation: Animation) {}
                })
            }
    }

}


