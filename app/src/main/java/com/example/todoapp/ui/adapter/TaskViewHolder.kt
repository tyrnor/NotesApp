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
        binding.textViewTitle.text = task.title

        setIconVisibility(task.isIconsVisible)

        appearAnimation = AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_left)
        appearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                task.isIconsVisible = true
                onStateChange(true)
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        disappearAnimation = AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_out_left)
        disappearAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                task.isIconsVisible = false
                onStateChange(false)
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
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
}


