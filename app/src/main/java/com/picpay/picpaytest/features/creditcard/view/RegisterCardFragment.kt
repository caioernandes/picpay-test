package com.picpay.picpaytest.features.creditcard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentRegisterCardBinding
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.utils.Constants.CREDIT_CARD
import com.picpay.picpaytest.utils.Constants.USER
import com.picpay.picpaytest.data.sharedpreferences.SharedPreferences
import com.picpay.picpaytest.utils.autoCleared
import com.picpay.picpaytest.extensions.toText
import com.picpay.picpaytest.extensions.toTextInt
import com.picpay.picpaytest.utils.NavigationUtil

class RegisterCardFragment : Fragment() {

    private var binding: FragmentRegisterCardBinding by autoCleared()
    private lateinit var mUser: User
    private lateinit var mCreditCard: CreditCard

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireArguments().apply {
            mUser = getParcelable(USER) ?: User()
            mCreditCard = getParcelable(CREDIT_CARD) ?: CreditCard()
        }
        binding = FragmentRegisterCardBinding.inflate(inflater, container, false)
        buttonRegisterCardClickListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFieldsCreditCard()
    }

    private fun setupFieldsCreditCard() = with(binding) {
        mCreditCard.let {
            textInputEditTextCardNumber.setText(it.cardNumber, TextView.BufferType.NORMAL)
            textInputEditTextCardholderName.setText(it.bearer, TextView.BufferType.NORMAL)
            textInputEditTextCvv.setText(it.cvv.toString(), TextView.BufferType.NORMAL)
            textInputEditTextDueData.setText(it.expiryDate, TextView.BufferType.NORMAL)
        }
    }

    private fun buttonRegisterCardClickListener() {
        binding.materialButtonRegisterCard.setOnClickListener { setupInsertCreditCard() }
    }

    private fun setupInsertCreditCard() {
        mCreditCard = CreditCard(
            cardNumber = binding.textInputEditTextCardNumber.toText(),
            bearer = binding.textInputEditTextCardholderName.toText(),
            cvv = binding.textInputEditTextCvv.toTextInt(),
            expiryDate = binding.textInputEditTextDueData.toText()
        )
        insertCreditCard()
        validateCreditCardToPayment()
    }

    private fun validateCreditCardToPayment() {
        if (mCreditCard.cardNumber.isNotEmpty()) {
            redirectPayment()
        } else {
            showMessageError()
        }
    }

    private fun insertCreditCard() {
        SharedPreferences.putObjectSharedPref(requireActivity(), CREDIT_CARD, mCreditCard)
    }

    private fun redirectPayment() {
        findNavController().navigate(
            R.id.action_registerCard_to_paymentFragment,
            getBundleToPayment(),
            NavigationUtil.options
        )
    }

    private fun getBundleToPayment() = Bundle().apply {
        putParcelable(USER, mUser)
        putParcelable(CREDIT_CARD, mCreditCard)
    }

    private fun showMessageError() {
        Toast.makeText(
            requireContext(), getString(R.string.error_credit_card), Toast.LENGTH_SHORT
        ).show()
    }
}