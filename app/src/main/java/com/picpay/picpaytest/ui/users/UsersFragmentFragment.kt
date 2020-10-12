package com.picpay.picpaytest.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.picpaytest.R
import com.picpay.picpaytest.databinding.FragmentUsersFragmentBinding
import com.picpay.picpaytest.utils.Resource
import com.picpay.picpaytest.utils.autoCleared
import com.picpay.picpaytest.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragmentFragment : Fragment(), UsersAdapter.UserItemListener {

    private var binding: FragmentUsersFragmentBinding by autoCleared()
    private val viewModel: UsersViewModel by viewModels()
    private val adapter by lazy {
        UsersAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewUsers()
        setupObservers()
    }

    private fun setupRecyclerViewUsers() {
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUsers.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBarUsers.visibility = View.GONE
                    it.data?.let { listUser ->
                        if (listUser.isNotEmpty()) adapter.setItems(ArrayList(listUser))
                    }
                }
                Resource.Status.ERROR -> Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
                Resource.Status.LOADING -> binding.progressBarUsers.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedUser(userId: Int) {
        findNavController().navigate(
            R.id.action_usersFragment_to_registerCardPresentation,
            bundleOf("id" to userId)
        )
    }
}