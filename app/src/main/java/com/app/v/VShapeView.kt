package com.app.v

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class AnimatedVShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val path = Path()
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.RED
    }

    private lateinit var animator: ValueAnimator

    init {
        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                updatePath(fraction)
                invalidate()
            }
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
        }
        animator.start()
    }

    private fun updatePath(fraction: Float) {
        path.reset()
        // Define the starting and ending points for the letter V
        val startX = width * 0.7f
        val startY = height * 0.5f
        val endX = width * 0.9f
        val endY = height * 0.9f

        // Calculate the current point based on the animated fraction
        val currentX = startX + (endX - startX) * fraction
        val currentY = startY + (endY - startY) * fraction

        // Draw the letter V shape
        path.moveTo(startX, startY)
        path.lineTo(currentX, currentY)
        path.lineTo(endX, endY)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }
}