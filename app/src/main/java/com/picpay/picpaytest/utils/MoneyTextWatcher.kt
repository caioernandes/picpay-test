package com.picpay.picpaytest.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.picpay.picpaytest.extensions.parseToBigDecimal
import java.lang.ref.WeakReference
import java.text.NumberFormat
import java.util.*

class MoneyTextWatcher(editText: EditText, locale: Locale? = null, private val callback: (String) -> Unit) : TextWatcher {

    private val editTextWeakReference: WeakReference<EditText> = WeakReference(editText)
    private val locale: Locale = locale ?: Locale.getDefault()

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        callback.invoke(s.toString())
    }

    override fun afterTextChanged(editable: Editable) {
        val editText = editTextWeakReference.get() ?: return
        editText.removeTextChangedListener(this)
        val parsed = editable.toString().parseToBigDecimal(locale)
        val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }
}