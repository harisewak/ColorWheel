package com.harisewak.colorwheel.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.harisewak.colorwheel.TAG
import com.harisewak.colorwheel.databinding.ActivityColorWheelBinding
import com.harisewak.colorwheel.util.ControlSelectionManager
import com.harisewak.colorwheel.customviews.ColorWheelView


class ColorWheelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColorWheelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColorWheelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {

        binding.flControl1.setOnClickListener {
            ControlSelectionManager.select(
                binding,
                ControlSelectionManager.SELECTED_CONTROL.CONTROL_1
            )
        }

        binding.flControl2.setOnClickListener {
            ControlSelectionManager.select(
                binding,
                ControlSelectionManager.SELECTED_CONTROL.CONTROL_2
            )
        }

        binding.flControl3.setOnClickListener {
            ControlSelectionManager.select(
                binding,
                ControlSelectionManager.SELECTED_CONTROL.CONTROL_3
            )
        }

        binding.colorWheel.colorListener = object: ColorWheelView.ColorListener {

            override fun onColorChanged(color: Int) {
                Log.d(TAG, "onColorChanged: $color")
                ControlSelectionManager.updateColor(binding, color)
            }

        }

    }

}