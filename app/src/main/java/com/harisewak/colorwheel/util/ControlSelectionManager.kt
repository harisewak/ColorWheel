package com.harisewak.colorwheel.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.harisewak.colorwheel.R
import com.harisewak.colorwheel.databinding.ActivityColorWheelBinding
import java.lang.Exception

object ControlSelectionManager {

    private var selected: SELECTED_CONTROL = SELECTED_CONTROL.CONTROL_1

    fun select(binding: ActivityColorWheelBinding, select: SELECTED_CONTROL) {

        if (selected != select) {

            if (select == SELECTED_CONTROL.CONTROL_1) {
                binding.flControl1.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_selected))
                if (selected == SELECTED_CONTROL.CONTROL_2) {
                    binding.flControl2.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_bg))
                }
                if (selected == SELECTED_CONTROL.CONTROL_3) {
                    binding.flControl3.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_bg))
                }
                selected = SELECTED_CONTROL.CONTROL_1
            }

            if (select == SELECTED_CONTROL.CONTROL_2) {
                binding.flControl2.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_selected))
                if (selected == SELECTED_CONTROL.CONTROL_1) {
                    binding.flControl1.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_bg))
                }
                if (selected == SELECTED_CONTROL.CONTROL_3) {
                    binding.flControl3.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_bg))
                }
                selected = SELECTED_CONTROL.CONTROL_2
            }

            if (select == SELECTED_CONTROL.CONTROL_3) {
                binding.flControl3.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_selected))
                if (selected == SELECTED_CONTROL.CONTROL_1) {
                    binding.flControl1.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_bg))
                }
                if (selected == SELECTED_CONTROL.CONTROL_2) {
                    binding.flControl2.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.color_control_bg))
                }
                selected = SELECTED_CONTROL.CONTROL_3
            }

        }
    }

    fun updateColor(binding: ActivityColorWheelBinding, color: Int) {
        val control = selectedControl(binding)
        control.setColorFilter(color)
    }

    private fun selectedControl(binding: ActivityColorWheelBinding): ImageView {
       return when (selected) {
            SELECTED_CONTROL.CONTROL_1 -> binding.ivControl1
            SELECTED_CONTROL.CONTROL_2 -> binding.ivControl2
            SELECTED_CONTROL.CONTROL_3 -> binding.ivControl3
       }
    }


    enum class SELECTED_CONTROL {
        CONTROL_1,
        CONTROL_2,
        CONTROL_3
    }
}