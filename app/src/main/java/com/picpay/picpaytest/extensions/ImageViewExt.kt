package com.picpay.picpaytest.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun ImageView.setImageFromNetWork(view: View, image: String) {
    Glide.with(view)
        .load(image)
        .transform(CircleCrop())
        .into(this)
}