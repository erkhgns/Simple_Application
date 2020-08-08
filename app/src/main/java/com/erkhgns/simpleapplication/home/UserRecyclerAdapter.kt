package com.erkhgns.simpleapplication.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erkhgns.simpleapplication.databinding.CardUserBinding
import com.erkhgns.simpleapplication.network.User

class UserRecyclerAdapter(private val users: List<User>, private val onclick: (User) -> Unit) :
    RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {
    inner class UserViewHolder(private val binding: CardUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onclick(users[adapterPosition])
            }
        }

        fun bind(user: User) {
            binding.apply {
                this.user = user
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            CardUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }
}