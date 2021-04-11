package com.picpay.picpaytest.data.sharedpreferences

import android.app.Activity
import android.content.Context
import com.google.gson.Gson

object SharedPreferences {

    inline fun <reified T>  putObjectSharedPref(activity: Activity, key: String, value: T) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val jsonObject = Gson().toJson(value)
            putString(key, jsonObject)
            commit()
        }
    }

    inline fun <reified T> getObjectSharedPref(activity: Activity, key: String): T? {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val json: String? = sharedPref.getString(key, "")
        return Gson().fromJson(json, T::class.java)
    }
}