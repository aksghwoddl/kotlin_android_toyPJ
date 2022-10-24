package com.lee.customseekbar.customview

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.lee.customseekbar.R

class CircleSeekBar(context : Context , attrs : AttributeSet) : View(context , attrs) {
    private var progress = 0f

    private var foregroundStrokeWidth = 0f
    private var backgroundStrokeWidth = 0f
    var color = Color.WHITE

    private var backgroundProgressColor = Color.GRAY

    private val startAngle = -90
    private var rectF : RectF? = null
    private var foregroundPaint : Paint? = null
    private var backgroundPaint : Paint? = null

    init{
        init(context , attrs)
    }

    private fun init(context: Context , attrs: AttributeSet) {
        rectF = RectF()
        val typedArray = context.theme.obtainStyledAttributes(attrs , R.styleable.CircleProgress , 0,0)
        try{
            progress = typedArray.getFloat(R.styleable.CircleProgress_az_progress , progress)
            foregroundStrokeWidth = typedArray.getDimension(R.styleable.CircleProgress_az_progressbar_width , foregroundStrokeWidth)
            backgroundStrokeWidth = typedArray.getDimension(R.styleable.CircleProgress_az_background_progressbar_width , backgroundStrokeWidth)
            color = typedArray.getColor(R.styleable.CircleProgress_az_progressbar_color , color)
            backgroundProgressColor = typedArray.getColor(R.styleable.CircleProgress_az_background_progressbar_color , backgroundProgressColor)
        } finally {
            typedArray.recycle()
        }

        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backgroundPaint!!.color = backgroundProgressColor
        backgroundPaint!!.style = Paint.Style.STROKE
        backgroundPaint!!.strokeWidth = backgroundStrokeWidth

        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        foregroundPaint!!.color = color
        foregroundPaint!!.style = Paint.Style.STROKE
        foregroundPaint!!.strokeWidth = foregroundStrokeWidth
    }

    fun setProgressValue(progress : Float){
        this.progress = if(progress <= 100) progress else 100f
        invalidate()
    }

    fun setColorValue(color : Int) {
        this.color = color
        foregroundPaint!!.color = color
        invalidate()
        requestLayout()
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        this.backgroundProgressColor = color
        backgroundPaint!!.color = backgroundProgressColor
        invalidate()
        requestLayout()
    }

    var progressBarWidth : Float
    get() = foregroundStrokeWidth
    set(strokeWidth) {
        this.foregroundStrokeWidth = strokeWidth
        foregroundPaint!!.strokeWidth = strokeWidth
        requestLayout()
        invalidate()
    }

    var backgroundProgressBarWidth : Float
    get() = backgroundStrokeWidth
    set(strokeWidth) {
        backgroundStrokeWidth = strokeWidth
        backgroundPaint!!.strokeWidth = strokeWidth
        requestLayout()
        invalidate()
    }

    fun setProgressWithAnimation(progress: Float) {
        setProgressWithAnimation(progress , 3000)
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun setProgressWithAnimation(progress: Float, duration: Int){
        val objectAnimator = ObjectAnimator.ofFloat(this , "progress" , progress)
        objectAnimator.duration = duration.toLong()
        objectAnimator.interpolator = DecelerateInterpolator()
        objectAnimator.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight , heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth , widthMeasureSpec)
        val min = Math.min(width , height)
        setMeasuredDimension(min , min)
        val highStroke = if(foregroundStrokeWidth > backgroundStrokeWidth) foregroundStrokeWidth else backgroundStrokeWidth
        rectF!![0 + highStroke / 2 , 0 + highStroke/2 , min - highStroke/2] = min - highStroke / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(rectF!! , backgroundPaint!!)
        val angle = 360 * progress / 100
        canvas.drawArc(rectF !! , startAngle.toFloat() , angle , false , foregroundPaint!!)
    }

}