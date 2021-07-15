package com.harisewak.colorwheel.customviews

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.harisewak.colorwheel.TAG


class ColorWheelView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var wheelBitmap: Bitmap? = null
    private lateinit var wheel: ImageView
    private val inverse = Matrix()

    var colorListener: ColorListener? = null

    var dX = 0f
    var dY = 0f
    var lastAction = 0

    init {
        initWheel()
    }


    fun colorFromBitmap(x: Int, y: Int): Int {

        wheel.isDrawingCacheEnabled = true
        val imgbmp = Bitmap.createBitmap(wheel.drawingCache)
        wheel.isDrawingCacheEnabled = false

        try {
            return imgbmp.getPixel(x, y)
        } catch (ignore: Exception) {
        }
        imgbmp.recycle()

        return -1
    }

    private fun initWheel() {
        wheel = ImageView(context)

        val wheelParams =
            LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        wheelParams.gravity = Gravity.CENTER

        wheel.setOnTouchListener { v, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                val x = event.x
                val y = event.y
                Log.d(TAG, "x: $x, y: $y")
//                val color = getColorFromBitmap(x, y)
                val color = colorFromBitmap(x.toInt(), y.toInt())
                colorListener?.onColorChanged(color)
                true
            }
            else false
        }

        addView(wheel, wheelParams)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, w, oldw, oldh)

        wheelBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888)
        wheel.setImageDrawable(ColorHsvPalette(resources, wheelBitmap))
        wheel.imageMatrix.invert(inverse)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }


    interface ColorListener {
        fun onColorChanged(color: Int)
    }

}