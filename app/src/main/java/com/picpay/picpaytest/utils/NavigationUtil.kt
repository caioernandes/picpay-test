package com.picpay.picpaytest.utils

import androidx.navigation.navOptions
import com.picpay.picpaytest.R

object NavigationUtil {

    val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }
}