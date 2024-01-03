package com.example.todoapp.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.domain.entities.Task
import com.example.todoapp.ui.core.listeners.OnSwipeTouchListener

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var appearAnimation: Animation
    private lateinit var disappearAnimation: Animation

    @SuppressLint("ClickableViewAccessibility")
    fun bind(task: Task, onStateChange: (Boolean) -> Unit) {
        setIconVisibility(task.isIconsVisible)
        setupAnimations(task, onStateChange)

        binding.textViewTitle.text = task.title
        binding.textViewTitle.setOnTouchListener(object : OnSwipeTouchListener(binding.root.context){

            override fun onSwipeLeft() {
                if (!task.isIconsVisible) {
                    binding.buttons.startAnimation(appearAnimation)
                    binding.buttons.visibility = View.VISIBLE
                }
            }

            override fun onSwipeRight() {
                if (task.isIconsVisible) {
                    binding.buttons.startAnimation(disappearAnimation)
                    binding.buttons.visibility = View.GONE
                }
            }
        })

    }

    private fun setIconVisibility(isVisible: Boolean) {
        binding.buttons.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun setupSlideInAnimation(task: Task, onStateChange: (Boolean) -> Unit){
        appearAnimation = createAnimation(R.anim.slide_in_left, task, onStateChange, true)
    }
    private fun setupSlideOutAnimation(task: Task, onStateChange: (Boolean) -> Unit){
        disappearAnimation = createAnimation(R.anim.slide_out_left, task, onStateChange, false)
    }

    private fun setupAnimations(task: Task, onStateChange: (Boolean) -> Unit) {
        setupSlideInAnimation(task, onStateChange)
        setupSlideOutAnimation(task, onStateChange)
    }

    private fun createAnimation(animationResId: Int, task: Task, onStateChange: (Boolean) -> Unit, isVisible: Boolean ) : Animation{
        val animation = AnimationUtils.loadAnimation(binding.root.context, animationResId)
        animation.setAnimationListener(createAnimationListener(task, onStateChange, isVisible))
        return animation
    }

    private fun createAnimationListener(task: Task, onStateChange: (Boolean) -> Unit, isVisible: Boolean): Animation.AnimationListener {
        return object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                task.isIconsVisible = isVisible
                onStateChange(isVisible)
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        }
    }
}


