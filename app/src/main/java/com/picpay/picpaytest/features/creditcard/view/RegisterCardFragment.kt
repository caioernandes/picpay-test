package com.picpay.picpaytest.features.creditcard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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