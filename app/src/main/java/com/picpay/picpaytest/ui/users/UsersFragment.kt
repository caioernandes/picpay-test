package com.picpay.picpaytest.ui.users

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentUsersBinding
import com.picpay.picpaytest.model.User
import com.picpay.picpaytest.ui.users.adapter.UsersAdapter
import com.picpay.picpaytest.utils.*
import com.picpay.picpaytest.viewmodel.creditcard.CreditCardViewModel
import com.picpay.picpaytest.viewmodel.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment(), UsersAdapter.UserItemListener, SearchView.OnQueryTextListener {

    private var binding: FragmentUsersBinding by autoCleared()
    private val viewModelUsers: UsersViewModel by viewModels()
    private val viewModelCreditCard: CreditCardViewModel by viewModels()

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
        viewModelUsers.users.observe(viewLifecycleOwner, {
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
        })
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
        viewModelCreditCard.loadCreditCard()
        observeCreditCard(user)
    }

    private fun observeCreditCard(user: User) {
        viewModelCreditCard.creditCard.observe(viewLifecycleOwner, { creditCard ->
            if (creditCard.isEmpty()) {
                findNavController().navigate(
                    R.id.action_usersFragment_to_registerCardPresentation,
                    bundleOf("user" to user)
                )
            } else {
                val bundle = Bundle()
                bundle.putParcelable("creditCard", creditCard.first())
                bundle.putParcelable("user", user)
                findNavController().navigate(
                    R.id.action_usersFragment_to_paymentFragment,
                    bundle
                )
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(query: String?): Boolean {
        query?.let { adapter.filter(it) }
        return false
    }
}