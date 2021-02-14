package com.picpay.picpaytest.features.users.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentUsersBinding
import com.picpay.picpaytest.features.creditcard.viewmodel.CreditCardViewModel
import com.picpay.picpaytest.features.users.model.User
import com.picpay.picpaytest.features.users.view.adapter.UsersAdapter
import com.picpay.picpaytest.features.users.viewmodel.UsersViewModel
import com.picpay.picpaytest.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment(), UsersAdapter.UserItemListener, SearchView.OnQueryTextListener {

    private var binding: FragmentUsersBinding by autoCleared()
    private val viewModelUsers: UsersViewModel by viewModel()
    private val viewModelCreditCard: CreditCardViewModel by viewModel()

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    private val adapter by lazy {
        UsersAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewUsers()
        observeUsers()
        bindListeners()
        setupLayoutSearchView()
    }

    private fun setupRecyclerViewUsers() {
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUsers.adapter = adapter
    }

    private fun observeUsers() {
        viewModelUsers.users.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBarUsers.gone()
                    it.data?.let { listUser ->
                        if (listUser.isNotEmpty()) adapter.setItems(ArrayList(listUser))
                    }
                }
                Resource.Status.ERROR -> Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
                Resource.Status.LOADING -> binding.progressBarUsers.visible()
            }
        }
    }

    private fun bindListeners() {
        binding.searchViewContacts.setOnQueryTextListener(this)
    }

    private fun setupLayoutSearchView() {
        findChildrenByClass(binding.searchViewContacts, EditText::class.java)?.let {
            for (editText in it) {
                editText.setHintTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPlaceHolder
                    )
                )
                editText.setTextColor(Color.WHITE)
            }
        }
    }

    override fun onClickedUser(user: User) {
        val cardNumber = SharedPreferences.getStringSharedPref(
            requireActivity(),
            getString(R.string.card_number_user_key)
        )
        viewModelCreditCard.loadCreditCard(cardNumber)
        observeCreditCard(user)
    }

    private fun observeCreditCard(user: User) {
        viewModelCreditCard.creditCardLiveData?.observe(viewLifecycleOwner) { creditCard ->
            if (creditCard.cardNumber.isEmpty()) {
                findNavController().navigate(
                    R.id.action_usersFragment_to_registerCardPresentation,
                    bundleOf("user" to user),
                    options
                )
            } else {
                val bundle = Bundle()
                bundle.putParcelable("creditCard", creditCard)
                bundle.putParcelable("user", user)
                findNavController().navigate(
                    R.id.action_usersFragment_to_paymentFragment,
                    bundle,
                    options
                )
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean = searchFilter(query)

    override fun onQueryTextChange(query: String?): Boolean = searchFilter(query)

    private fun searchFilter(query: String?): Boolean {
        query?.let { adapter.filter(it) }
        return false
    }
}