package com.picpay.picpaytest.utils

import android.view.View
import android.view.ViewGroup

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun <V : View?> findChildrenByClass(viewGroup: ViewGroup, clazz: Class<V>): Collection<V>? {
    return gatherChildrenByClass(viewGroup, clazz, ArrayList())
}

private fun <V : View?> gatherChildrenByClass(
    viewGroup: ViewGroup,
    clazz: Class<V>,
    childrenFound: MutableCollection<V>
): Collection<V>? {
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