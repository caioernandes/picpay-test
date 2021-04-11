package com.picpay.picpaytest.extensions

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun <V : View?> findChildrenByClass(viewGroup: ViewGroup, clazz: Class<V>): Collection<V> {
    return gatherChildrenByClass(viewGroup, clazz, ArrayList())
}

private fun <V : View?> gatherChildrenByClass(
    viewGroup: ViewGroup,
    clazz: Class<V>,
    childrenFound: MutableCollection<V>
): Collection<V> {
    for (i in 0 until viewGroup.childCount) {
        val child = viewGroup.getChildAt(i)
        if (clazz.isAssignableFrom(child.javaClass)) {
            childrenFound.add(child as V)
        }
        if (child is ViewGroup) {
            gatherChildrenByClass(child, clazz, childrenFound)
        }
    }
    return childrenFound
}

fun TextInputEditText.toText() = this.text?.toString().orEmpty()

fun TextInputEditText.toTextInt() = this.text?.toString()?.toInt() ?: 0

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.postDelayed(callback: () -> Unit) {
    Handler().postDelayed({ callback.invoke() }, 2000)
}

fun <T: View> Fragment.findViewById(id: Int): T {
    return requireActivity().findViewById(id)
}