package com.example.todoapp.ui.core.listeners

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
//        when (event.action) {
//            MotionEvent.ACTION_DOWN -> v.parent.requestDisallowInterceptTouchEvent(true)
//            MotionEvent.ACTION_UP -> v.parent.requestDisallowInterceptTouchEvent(false)
//        }
       // return gestureDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return when (event.action) {
            MotionEvent.ACTION_UP -> {
                onClick(v) // Call onClick when the user lifts their finger
                true
            }
            else -> gestureDetector.onTouchEvent(event)
        }
    }

    open fun onClick(v: View) {
        // Implement click action
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


        override fun onDown(e: MotionEvent): Boolean {
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
                val distance = Math.sqrt((diffX * diffX + diffY * diffY).toDouble())
                val angle = Math.toDegrees(Math.atan2(diffY.toDouble(), diffX.toDouble()))

                if (distance > SWIPE_THRESHOLD) {
                    if (Math.abs(angle) < 45 || Math.abs(angle) > 135) {
                        // Horizontal swipe
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    } else if (Math.abs(angle) >= 45 && Math.abs(angle) <= 135) {
                        // Vertical swipe
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }



    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}
}