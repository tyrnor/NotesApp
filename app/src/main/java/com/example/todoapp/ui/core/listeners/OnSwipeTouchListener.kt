package com.example.todoapp.ui.core.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.sqrt

open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    private var gestureDetector: GestureDetector = GestureDetector(context, GestureListener())
    private var isSwiped = false

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_UP) {
            isSwiped = false
        }
        return isSwiped
    }


    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            isSwiped = false
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1 == null) return false
            var result = false
            try {
                val diffY = e2.y - e1.y
                val diffX = e2.x - e1.x
                if (sqrt((diffX * diffX + diffY * diffY).toDouble()) > SWIPE_THRESHOLD) {
                    if (abs(diffX) > abs(diffY)) {
                        if (diffX > 0) onSwipeRight() else onSwipeLeft()
                    } else {
                        if (diffY > 0) onSwipeBottom() else onSwipeTop()
                    }
                    isSwiped = true
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }
    }

    open fun onSwipeRight() {
        // Override this method in your listener to handle right swipe
    }

    open fun onSwipeLeft() {
        // Override this method in your listener to handle left swipe
    }

    open fun onSwipeTop() {
        // Override this method in your listener to handle top swipe
    }

    open fun onSwipeBottom() {
        // Override this method in your listener to handle bottom swipe
    }
}
