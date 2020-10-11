package com.picpay.picpaytest.ui.registercard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.picpay.picpaytest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterCardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_card, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterCardFragment()
    }
}