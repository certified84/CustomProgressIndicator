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

package com.certified.google_pay

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.certified.google_pay.Constants.GOOGLE_PAY_REQUEST_CODE
import com.certified.google_pay.databinding.GooglePayButtonBinding
import com.google.android.gms.tasks.Task
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData
import com.google.android.gms.wallet.PaymentDataRequest
import com.google.android.gms.wallet.PaymentsClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class BuyWithGooglePay @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) :
    ConstraintLayout(context, attributeSet, defStyle) {

    private val binding =
        GooglePayButtonBinding.inflate(LayoutInflater.from(context), this, true)

    //    Global variables
    private var coroutineScope: CoroutineScope? = CoroutineScope(Dispatchers.Main)
    private var textColor: Int = Color.parseColor("#FFFFFF")

    private var _price = 0L
    private var _shippingCost = 0L
    private var _paymentsClient: PaymentsClient? = null
    private var _activity: Activity? = null

    var price: Long
        get() = _price
        set(value) {
            _price = value
        }

    var shippingCost: Long
        get() = _shippingCost
        set(value) {
            _shippingCost = value
        }

    init {
        binding.btnGooglePay.setOnClickListener {
            requestPayment(_price, _shippingCost)
        }
    }

    fun setVisibility(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }


    //    Button Text
    fun setText(value: CharSequence) {
        binding.btnGooglePay.text = value
    }

    fun setText(value: CharSequence, type: TextView.BufferType) {
        binding.btnGooglePay.setText(value, type)
    }

    fun setText(value: CharArray, start: Int, len: Int) {
        binding.btnGooglePay.setText(value, start, len)
    }

    fun setText(@StringRes resId: Int) {
        binding.btnGooglePay.setText(resId)
    }

    fun setText(@StringRes resId: Int, type: TextView.BufferType) {
        binding.btnGooglePay.setText(resId, type)
    }

    //    Text colors
//    fun setTextColor(colorString: String) {
//        binding.btnGooglePay.setTextColor(ColorStateList.valueOf(Color.parseColor(colorString)))
//    }
//
//    fun setTextColor(colorStateList: ColorStateList) {
//        binding.btnGooglePay.setTextColor(colorStateList)
//    }
//
//    fun setTextColorInt(@ColorInt colorInt: Int) {
//        binding.btnGooglePay.setTextColor(ColorStateList.valueOf(colorInt))
//    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    fun setTextColor(@ColorRes colorRes: Int) {
//        binding.btnGooglePay.setTextColor(resources.getColorStateList(colorRes, context.theme))
//    }

    //    Text typeface
    fun setTypeface(@FontRes font: Int, @StyleRes style: Int) {
        val typeface1 = ResourcesCompat.getFont(context, font)
        binding.btnGooglePay.setTypeface(typeface1, style)
    }

    fun setTypeface(font: Int) {
        val typeface1 = ResourcesCompat.getFont(context, font)
        binding.btnGooglePay.typeface = typeface1
    }

    //    Text size
    fun setTextSize(size: Float) {
        binding.btnGooglePay.textSize = size
    }

    fun initialize(activity: Activity) {
        _activity = activity
        _paymentsClient = PaymentsUtil.createPaymentsClient(activity)
        Log.d("TAG", "initialize: Called")
    }


    private fun requestPayment(price: Long, shippingCost: Long) {
        Log.d("TAG", "requestPayment: Called")
        Log.d("TAG", "requestPayment: $price, $shippingCost")
        if (_price == 0L || _shippingCost == 0L) {
            Toast.makeText(
                context,
                "You must set price and shipping cost first",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (_activity == null) {
            Toast.makeText(context, "You must call initialize() first", Toast.LENGTH_SHORT).show()
            return
        }
        _paymentsClient = PaymentsUtil.createPaymentsClient(_activity!!)
        val task = getLoadPaymentDataTask(price + shippingCost)
        task?.let { AutoResolveHelper.resolveTask(it, _activity!!, GOOGLE_PAY_REQUEST_CODE) }
    }


    /**
     * Creates a [Task] that starts the payment process with the transaction details included.
     *
     * @param priceCents the price to show on the payment sheet.
     * @return a [Task] with the payment information.
     * @see [](https://developers.google.com/android/reference/com/google/android/gms/wallet/PaymentsClient#loadPaymentData(com.google.android.gms.wallet.PaymentDataRequest)
    ) */
    private fun getLoadPaymentDataTask(priceCents: Long): Task<PaymentData>? {
        val paymentDataRequestJson = PaymentsUtil.getPaymentDataRequest(priceCents)
        val request = PaymentDataRequest.fromJson(paymentDataRequestJson.toString())
        return _paymentsClient?.loadPaymentData(request)
    }
}