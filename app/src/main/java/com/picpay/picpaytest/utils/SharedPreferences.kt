package com.picpay.picpaytest.utils

import android.app.Activity
import android.content.Context
import com.picpay.picpaytest.R

object SharedPreferences {

    fun putStringSharedPref(activity: Activity, key: String, value: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            commit()
        }
    }

    fun getStringSharedPref(activity: Activity, key: String): String {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val defaultValue = activity.resources.getString(R.string.saved_high_score_key)
        return sharedPref.getString(key, defaultValue).orEmpty()
    }
}