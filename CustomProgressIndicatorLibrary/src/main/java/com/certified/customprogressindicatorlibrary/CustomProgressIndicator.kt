/*
 * Copyright 2022 Samson Achiaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.certified.customprogressindicatorlibrary

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.annotation.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.certified.customprogressindicatorlibrary.databinding.CustomProgressIndicatorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomProgressIndicator @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    ConstraintLayout(context, attributeSet, defStyle) {

    //    CustomProgressIndicator
    private val binding =
        CustomProgressIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

    //    Global variables
    private var coroutineScope: CoroutineScope? = CoroutineScope(Dispatchers.Main)
    private var progressIndicatorColor: Int = Color.parseColor("#000000")
    private var textColor: Int = Color.parseColor("#FFFFFF")
    private var animate = true

    fun setVisibility(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }


    //    Loading Text
    fun setText(value: CharSequence) {
        binding.tvLoading.text = value
    }

    fun setText(value: CharSequence, type: TextView.BufferType) {
        binding.tvLoading.setText(value, type)
    }

    fun setText(value: CharArray, start: Int, len: Int) {
        binding.tvLoading.setText(value, start, len)
    }

    fun setText(@StringRes resId: Int) {
        binding.tvLoading.setText(resId)
    }

    fun setText(@StringRes resId: Int, type: TextView.BufferType) {
        binding.tvLoading.setText(resId, type)
    }

    //    Text colors
    fun setTextColor(colorString: String) {
        binding.tvLoading.setTextColor(ColorStateList.valueOf(Color.parseColor(colorString)))
    }

    fun setTextColor(colorStateList: ColorStateList) {
        binding.tvLoading.setTextColor(colorStateList)
    }

    fun setTextColorInt(@ColorInt colorInt: Int) {
        binding.tvLoading.setTextColor(ColorStateList.valueOf(colorInt))
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setTextColor(@ColorRes colorRes: Int) {
        binding.tvLoading.setTextColor(resources.getColorStateList(colorRes, context.theme))
    }

    //    Text typeface
    fun setTypeface(@FontRes font: Int, @StyleRes style: Int) {
        val typeface1 = ResourcesCompat.getFont(context, font)
        binding.tvLoading.setTypeface(typeface1, style)
    }

    fun setTypeface(font: Int) {
        val typeface1 = ResourcesCompat.getFont(context, font)
        binding.tvLoading.typeface = typeface1
    }

    //    Text size
    fun setTextSize(size: Float) {
        binding.tvLoading.textSize = size
    }

    //    Progress Indicator Color
    fun setProgressIndicatorColor(colorString: String) {
        binding.indicator.setIndicatorColor(Color.parseColor(colorString))
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    fun setProgressIndicatorColor(@ColorRes color: Int, theme: Resources.Theme?) {
//        binding.indicator.setIndicatorColor()
//    }

    //    Track Color
    fun setTrackColor(colorString: String) {
        binding.indicator.trackColor = Color.parseColor(colorString)
    }

//    fun setTrackColor(@ColorInt color: Int) {
//        binding.indicator.trackColor = color
//    }

    //    App Icon
    fun setImageResource(@DrawableRes redId: Int) {
        binding.ivLogo.setImageResource(redId)
    }

    fun startAnimation() {
        animate = true
        coroutineScope = CoroutineScope(Dispatchers.Main)
        binding.apply {
            val animZoomIn = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
            val animZoomOut = AnimationUtils.loadAnimation(context, R.anim.zoom_out)
            coroutineScope?.launch {
                while (animate) {
                    delay(800L)
                    ivLogo.startAnimation(animZoomOut)
                    delay(800L)
                    ivLogo.startAnimation(animZoomIn)
                    Log.d("TAG", "startAnimation: Animating")
                }
            }
        }
    }

    fun stopAnimation() {
        animate = false
        coroutineScope = null
        binding.ivLogo.clearAnimation()
        Log.d("TAG", "stopAnimation: Stopped animating")
    }
}