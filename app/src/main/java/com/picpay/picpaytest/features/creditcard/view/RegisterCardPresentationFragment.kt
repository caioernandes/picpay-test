package com.picpay.picpaytest.features.creditcard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentRegisterCardPresentationBinding
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.utils.Constants.USER
import com.picpay.picpaytest.utils.NavigationUtil
import com.picpay.picpaytest.utils.autoCleared

class RegisterCardPresentationFragment : Fragment() {

    private var binding: FragmentRegisterCardPresentationBinding by autoCleared()
    private var mUser: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mUser = requireArguments().getParcelable(USER)
        binding = FragmentRegisterCardPresentationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
    }

    private fun bindListeners() {
        binding.materialButtonRegisterCard.setOnClickListener {
            findNavController().navigate(
                R.id.action_registerCardPresentation_to_registerCard,
                bundleOf(USER to mUser),
                NavigationUtil.options
            )
        }
    }
}