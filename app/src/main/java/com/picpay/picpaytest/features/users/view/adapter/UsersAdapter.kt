package com.picpay.picpaytest.features.users.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.picpaytest.databinding.ItemUserBinding
import com.picpay.picpaytest.extensions.setImageFromNetWork
import com.picpay.picpaytest.features.users.model.User
import java.util.*
import kotlin.collections.ArrayList

class UsersAdapter(private val listener: UserItemListener) :
    RecyclerView.Adapter<UserViewHolder>() {

    interface UserItemListener {
        fun onClickedUser(user: User)
    }

    private val usersList = ArrayList<User>()
    private var usersListBackup: List<User>? = null

    fun setItems(items: ArrayList<User>) {
        usersList.clear()
        usersList.addAll(items)
        usersListBackup = items
        notifyDataSetChanged()
    }

    fun filter(searchText: String) {
        val searchTextLowerCase = searchText.toLowerCase(Locale.getDefault())
        usersList.clear()
        if (searchTextLowerCase.isEmpty()) {
            usersListBackup?.let {
                usersList.addAll(it)
            }
        } else {
            usersListBackup?.let { users ->
                for (item in users) {
                    if (item.name.toLowerCase(Locale.getDefault()).contains(searchTextLowerCase)) {
                        usersList.add(item)
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding: ItemUserBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(usersList[position])
}

class UserViewHolder(
    private val itemBinding: ItemUserBinding,
    private val listener: UsersAdapter.UserItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var user: User

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: User) {
        this.user = item
        with(itemBinding) {
            appCompatTextViewUserNameContact.text = item.userName
            appCompatTextViewNameContact.text = item.name
            appCompatImageViewAvatarContact.setImageFromNetWork(itemBinding.root, item.image)
        }
    }

    override fun onClick(v: View?) {
        listener.onClickedUser(user)
    }
}