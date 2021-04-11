package com.picpay.picpaytest.features.payment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentPaymentBinding
import com.picpay.picpaytest.extensions.*
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.payment.model.ReceiptPayment
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.utils.*
import com.picpay.picpaytest.utils.Constants.CREDIT_CARD
import com.picpay.picpaytest.utils.Constants.RECEIPT_PAYMENT
import com.picpay.picpaytest.utils.Constants.USER
import java.math.BigDecimal

class PaymentFragment : Fragment() {

    private var binding: FragmentPaymentBinding by autoCleared()
    private var mUser: User? = null
    private var mCreditCard: CreditCard? = null
    private var mReceiptPayment: ReceiptPayment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireArguments().apply {
            mUser = getParcelable(USER)
            mCreditCard = getParcelable(CREDIT_CARD)
        }
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDataUser()
        showDataCreditCard()
        editCreditCardListener()
        executePaymentListener()
        paymentAmountTextChangeListener()
    }

    private fun paymentAmountTextChangeListener() = with(binding) {
        appCompatEditTextPaymentAmount.apply {
            addTextWatcher { text ->
                materialButtonPayment.apply {
                    val mustEnableButton =
                        text.isNotEmpty() && text.parseToBigDecimal() > BigDecimal.ZERO
                    if (mustEnableButton) {
                        enable()
                    } else {
                        disable()
                    }
                }
            }
        }
    }

    private fun executePaymentListener() = with(binding) {
        materialButtonPayment.setOnClickListener {
            setupReceiptPayment()
            hideKeyboard()
            materialButtonPayment.isClickable = false
            appCompatEditTextPaymentAmount.apply {
                clearFocus()
                isEnabled = true
                isClickable = false
            }
            showLoading()
            postDelayed {
                navigateToUsersList()
                hideLoading()
            }
        }
    }

    private fun navigateToUsersList() {
        findNavController().navigate(
            R.id.action_paymentFragment_to_usersFragment,
            bundleOf(RECEIPT_PAYMENT to mReceiptPayment),
            NavigationUtil.options
        )
    }

    private fun setupReceiptPayment() {
        mReceiptPayment = ReceiptPayment(
            userAvatar = mUser?.image.orEmpty(),
            userName = mUser?.userName.orEmpty(),
            currentDate = DateUtil.getCurrentDate(),
            currentHour = DateUtil.getCurrentTime(),
            transactionNumber = rand(111111, 999999).toString(),
            numberCreditCard = mCreditCard?.cardNumber.orEmpty(),
            valueCreditCardPaid = getValuePaymentAmountFromEditText(),
            totalAmountPaid = getValuePaymentAmountFromEditText()
        )
    }

    private fun getValuePaymentAmountFromEditText(): String {
        return binding.appCompatEditTextPaymentAmount.text.toString()
    }

    private fun showDataUser() = with(binding) {
        appCompatImageViewAvatarContact.setImageFromNetWork(binding.root, mUser?.image.orEmpty())
        appCompatTextViewUserNameContact.text = mUser?.userName
    }

    private fun editCreditCardListener() {
        binding.appCompatTextViewEditCreditCard.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable(USER, mUser)
                putParcelable(CREDIT_CARD, mCreditCard)
            }
            findNavController().navigate(
                R.id.action_paymentFragment_to_registerCard,
                bundle,
                NavigationUtil.options
            )
        }
    }

    private fun showDataCreditCard() = with(binding) {
        appCompatTextViewDataCreditCard.text = String.format(
            getString(R.string.info_credit_card),
            mCreditCard?.cardNumber?.takeLast(4)
        )
    }

    private fun showLoading() {
        binding.progressBarPayment.isVisible = true
    }

    private fun hideLoading() {
        binding.progressBarPayment.isVisible = false
    }
}