package com.picpay.picpaytest.extensions

import android.widget.EditText
import com.picpay.picpaytest.utils.MoneyTextWatcher
import java.util.*

fun EditText.addTextWatcher(callback: (String) -> Unit) {
    val locale = Locale("pt", "BR")
    this.addTextChangedListener(MoneyTextWatcher(this, locale, callback))
}