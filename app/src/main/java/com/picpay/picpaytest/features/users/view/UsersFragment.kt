package com.picpay.picpaytest.features.users.view

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.picpay.picpaytest.R
import com.picpay.picpaytest.data.sharedpreferences.SharedPreferences
import com.picpay.picpaytest.databinding.FragmentUsersBinding
import com.picpay.picpaytest.extensions.*
import com.picpay.picpaytest.features.creditcard.model.CreditCard
import com.picpay.picpaytest.features.payment.model.ReceiptPayment
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.features.users.view.adapter.UsersAdapter
import com.picpay.picpaytest.features.users.viewmodel.UsersViewModel
import com.picpay.picpaytest.utils.Constants.CREDIT_CARD
import com.picpay.picpaytest.utils.Constants.RECEIPT_PAYMENT
import com.picpay.picpaytest.utils.Constants.USER
import com.picpay.picpaytest.utils.NavigationUtil
import com.picpay.picpaytest.utils.Resource
import com.picpay.picpaytest.utils.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment(), UsersAdapter.UserItemListener, SearchView.OnQueryTextListener {

    private var binding: FragmentUsersBinding by autoCleared()
    private val viewModelUsers: UsersViewModel by viewModel()

    private lateinit var mUser: User
    private lateinit var mReceiptPayment: ReceiptPayment

    private val adapter by lazy { UsersAdapter(this) }

    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(binding.materialCardViewReceiptPayment)
    }

    private lateinit var mImageUserReceiptPayment: AppCompatImageView
    private lateinit var mNameUserReceiptPayment: AppCompatTextView
    private lateinit var mDateTimeReceiptPayment: AppCompatTextView
    private lateinit var mNumberTransactionReceiptPayment: AppCompatTextView
    private lateinit var mInfoCreditCardReceiptPayment: AppCompatTextView
    private lateinit var mValueCreditCardReceiptPayment: AppCompatTextView
    private lateinit var mValueTotalPaid: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            mReceiptPayment = arguments?.getParcelable(RECEIPT_PAYMENT) ?: ReceiptPayment()
            initComponentsFromBottomSheet()
            setupInfoBottomSheet()
        }
        setupBottomSheetBehavior()
        setupRecyclerViewUsers()
        observeUsers()
        bindListeners()
        setupLayoutSearchView()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    private fun initComponentsFromBottomSheet() {
        mImageUserReceiptPayment = findViewById(R.id.appCompatImageViewAvatarContactReceiptPayment)
        mNameUserReceiptPayment = findViewById(R.id.appCompatTextViewUserNameContactReceiptPayment)
        mDateTimeReceiptPayment = findViewById(R.id.appCompatTextViewDateTimeReceiptPayment)
        mNumberTransactionReceiptPayment =
            findViewById(R.id.appCompatTextViewNumberTransactionReceiptPayment)
        mInfoCreditCardReceiptPayment = findViewById(R.id.appCompatTextViewInfoCreditCard)
        mValueCreditCardReceiptPayment = findViewById(R.id.appCompatTextViewValueCreditCard)
        mValueTotalPaid = findViewById(R.id.appCompatTextViewValueTotalPaid)
    }

    private fun setupInfoBottomSheet() = with(binding) {
        if (mReceiptPayment.transactionNumber.isNotEmpty()) {
            materialCardViewReceiptPayment.isVisible = true
            mReceiptPayment.let { receipt ->
                mImageUserReceiptPayment.setImageFromNetWork(root, receipt.userAvatar)
                mNameUserReceiptPayment.text = receipt.userName
                mDateTimeReceiptPayment.text = String.format(
                    getString(R.string.datetime_receipt_payment),
                    receipt.currentDate,
                    receipt.currentHour
                )
                mNumberTransactionReceiptPayment.text = String.format(
                    getString(R.string.number_transaction_receipt_payment),
                    receipt.transactionNumber
                )
                mInfoCreditCardReceiptPayment.text = String.format(
                    getString(R.string.info_credit_card),
                    receipt.numberCreditCard.takeLast(4)
                )
                mValueCreditCardReceiptPayment.text = receipt.valueCreditCardPaid
                mValueTotalPaid.text = receipt.totalAmountPaid
            }
        }
    }

    private fun setupBottomSheetBehavior() {
        val bottomSheet: View = binding.materialCardViewReceiptPayment
        bottomSheetBehavior.peekHeight = resources.getDimensionPixelOffset(
            R.dimen.bottom_sheet_peek_height
        )
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheet.gone()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        val childLayoutParams: ViewGroup.LayoutParams = bottomSheet.layoutParams
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        childLayoutParams.height = displayMetrics.heightPixels
        bottomSheet.layoutParams = childLayoutParams
    }

    private fun setupRecyclerViewUsers() {
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUsers.adapter = adapter
    }

    private fun observeUsers() {
        viewModelUsers.users.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> showListUsers(it.data)
                Resource.Status.ERROR -> showMessageError(it.message)
                Resource.Status.LOADING -> showProgressBarLoading()
            }
        }
    }

    private fun showListUsers(users: List<User>?) {
        binding.progressBarUsers.gone()
        users?.let { listUser ->
            if (listUser.isNotEmpty()) {
                adapter.setItems(ArrayList(listUser))
            }
        }
    }

    private fun showMessageError(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBarLoading() {
        binding.progressBarUsers.visible()
    }

    private fun bindListeners() {
        binding.searchViewContacts.setOnQueryTextListener(this)
    }

    private fun setupLayoutSearchView() {
        findChildrenByClass(binding.searchViewContacts, EditText::class.java).let {
            for (editText in it) {
                editText.setHintTextColor(
                    ContextCompat.getColor(requireContext(), R.color.colorPlaceHolder)
                )
                editText.setTextColor(Color.WHITE)
            }
        }
    }

    override fun onClickedUser(user: User) {
        mUser = user
        validateCreditCardToPayment()
    }

    private fun validateCreditCardToPayment() {
        val creditCard = SharedPreferences.getObjectSharedPref<CreditCard>(
            requireActivity(),
            CREDIT_CARD
        )
        if (creditCard == null) {
            navigateToRegisterCardPresentation()
        } else {
            val bundle = Bundle().apply {
                putParcelable(CREDIT_CARD, creditCard)
                putParcelable(USER, mUser)
            }
            navigateToPayment(bundle)
        }
    }

    private fun navigateToRegisterCardPresentation() {
        findNavController().navigate(
            R.id.action_usersFragment_to_registerCardPresentation,
            bundleOf(USER to mUser),
            NavigationUtil.options
        )
    }

    private fun navigateToPayment(bundle: Bundle) {
        findNavController().navigate(
            R.id.action_usersFragment_to_paymentFragment,
            bundle,
            NavigationUtil.options
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean = searchFilter(query)

    override fun onQueryTextChange(query: String?): Boolean = searchFilter(query)

    private fun searchFilter(query: String?): Boolean {
        query?.let { adapter.filter(it) }
        return false
    }
}