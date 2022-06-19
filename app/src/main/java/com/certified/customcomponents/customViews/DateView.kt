package com.certified.customcomponents.customViews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import com.certified.customcomponents.utils.currentDate
import com.certified.customcomponents.utils.formatReminderDate
import com.google.android.material.textview.MaterialTextView


class DateView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : MaterialTextView(context, attributeSet, defStyle) {

    /*listeners*/
    private var buttonListener: OnButtonClickListener? = null

    //    Date value
    fun setDate(value: Long?) {
        text =
            if (value != null) formatReminderDate(value) else formatReminderDate(currentDate().timeInMillis)
    }

    /*Text color*/
    fun setDateColor(colorString: String) {
        setTextColor(ColorStateList.valueOf(Color.parseColor(colorString)))
    }

    fun setDateColor(colorStateList: ColorStateList) {
        setTextColor(colorStateList)
    }

    fun setDateColor(@ColorInt colorInt: Int) {
        setTextColor(ColorStateList.valueOf(colorInt))
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    fun setDateColor(@ColorRes colorRes: Int, nothing: Nothing?) {
//        setTextColor(resources.getColorStateList(colorRes, context.theme))
//    }

    fun setDateFontStyle(@FontRes font: Int, @StyleRes style: Int) {
        val typeface1 = ResourcesCompat.getFont(context, font)
        setTypeface(typeface1, style)
    }

    fun setDateFontStyle(font: Int) {
        val typeface1 = ResourcesCompat.getFont(context, font)
        typeface = typeface1
    }

    /*listeners*/
    fun setOnDateClickListener(listener: OnButtonClickListener) {
        buttonListener = listener
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        buttonListener?.onClick(text.toString())
        Log.d("TAG", "setOnClickListener: ${text.toString()}")
    }
}

interface OnButtonClickListener {
    fun onClick(date: String)
}