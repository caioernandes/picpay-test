package com.picpay.picpaytest.extensions

import android.widget.Button

fun Button.enable() {
    this.alpha = 1.0f
    this.isClickable = true
    this.isEnabled = true
}

fun Button.disable() {
    this.alpha = .9f
    this.isClickable = false
    this.isEnabled = false
}