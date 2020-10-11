package com.picpay.picpaytest.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.picpay.picpaytest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_fragment, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = UsersFragmentFragment()
    }
}