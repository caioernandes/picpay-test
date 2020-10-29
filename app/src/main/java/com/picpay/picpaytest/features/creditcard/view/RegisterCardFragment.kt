package com.picpay.picpaytest.features.creditcard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentRegisterCardBinding
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.creditcard.model.CreditCardInsert
import com.picpay.picpaytest.features.creditcard.viewmodel.CreditCardViewModel
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterCardFragment : Fragment() {

    private var binding: FragmentRegisterCardBinding by autoCleared()
    private val creditCardViewModel: CreditCardViewModel by viewModels()

    private var user: User? = null
    private lateinit var creditCard: CreditCardInsert

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = arguments?.getParcelable("user")
        binding = FragmentRegisterCardBinding.inflate(inflater, container, false)

        buttonRegisterCardClickListener()
        setupFieldsCreditCard()

        return binding.root
    }

    private fun setupFieldsCreditCard() {
        if (::creditCard.isInitialized) {
            binding.textInputEditTextCardNumber.setText(
                creditCard.cardNumber,
                TextView.BufferType.NORMAL
            )
            binding.textInputEditTextCardholderName.setText(
                creditCard.bearer,
                TextView.BufferType.NORMAL
            )
            binding.textInputEditTextCvv.setText(
                creditCard.cvv.toString(),
                TextView.BufferType.NORMAL
            )
            binding.textInputEditTextDueData.setText(
                creditCard.expiryDate,
                TextView.BufferType.NORMAL
            )
        }
    }

    private fun buttonRegisterCardClickListener() {
        binding.materialButtonRegisterCard.setOnClickListener {
            observeInsertCreditCard()
            setupInsertCreditCard()
        }
    }

    private fun setupInsertCreditCard() {
        creditCard = CreditCardInsert(
            cardNumber = binding.textInputEditTextCardNumber.toText(),
            bearer = binding.textInputEditTextCardholderName.toText(),
            cvv = binding.textInputEditTextCvv.toTextInt(),
            expiryDate = binding.textInputEditTextDueData.toText()
        )

        SharedPreferences.putStringSharedPref(
            requireActivity(),
            getString(R.string.card_number_user_key),
            creditCard.cardNumber
        )
        creditCardViewModel.insertCreditCard(creditCard)
    }

    private fun observeInsertCreditCard() {
        creditCardViewModel.userInserted.observe(viewLifecycleOwner) {
            if (it) {
                creditCardViewModel.loadCreditCard(creditCard.cardNumber)
            }
        }
        creditCardViewModel.creditCardLiveData?.observe(viewLifecycleOwner) {
            if (it.cardNumber.isNotEmpty()) {
                redirectPayment()
            } else {
                showMessageError()
            }
        }
    }

    private fun redirectPayment() {
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
        findNavController().navigate(
            R.id.action_registerCard_to_paymentFragment,
            getBundleToPayment(),
            options
        )
    }

    private fun getBundleToPayment(): Bundle {
        val bundle = Bundle()
        bundle.putParcelable("user", user)
        bundle.putParcelable("creditCard", creditCard)
        return bundle
    }

    private fun showMessageError() {
        Toast.makeText(
            requireContext(), getString(R.string.error_credit_card), Toast.LENGTH_SHORT
        ).show()
    }
}