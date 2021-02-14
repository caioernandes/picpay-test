package com.picpay.picpaytest.features.payment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.picpay.picpaytest.databinding.FragmentPaymentBinding
import com.picpay.picpaytest.utils.autoCleared

class PaymentFragment : Fragment(), FragmentManager.OnBackStackChangedListener {

    private var binding: FragmentPaymentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onBackStackChanged() {
        Toast.makeText(requireContext(), "deu certo", Toast.LENGTH_SHORT).show()
    }
}