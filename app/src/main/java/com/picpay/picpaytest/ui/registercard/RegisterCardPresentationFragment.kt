package com.picpay.picpaytest.ui.registercard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentRegisterCardPresentationBinding
import com.picpay.picpaytest.model.User
import com.picpay.picpaytest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterCardPresentationFragment : Fragment() {

    private var binding: FragmentRegisterCardPresentationBinding by autoCleared()
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = arguments?.getParcelable("user")
        binding = FragmentRegisterCardPresentationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
    }

    private fun bindListeners() {
        binding.materialButtonRegisterCard.setOnClickListener {
            val options = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }
            findNavController().navigate(
                R.id.action_registerCardPresentation_to_registerCard,
                bundleOf("user" to user),
                options
            )
        }
    }
}