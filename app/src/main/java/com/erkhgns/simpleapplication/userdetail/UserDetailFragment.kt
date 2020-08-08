package com.erkhgns.simpleapplication.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erkhgns.simpleapplication.MainActivity
import com.erkhgns.simpleapplication.base.BaseFragment
import com.erkhgns.simpleapplication.databinding.FragmentUserDetailBinding
import com.erkhgns.simpleapplication.network.User

class UserDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentUserDetailBinding
    private lateinit var user: User

    companion object {
        fun newInstance(user:User): UserDetailFragment = UserDetailFragment().apply {
            this.user = user
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.user = user
        binding.activity = activity as MainActivity
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}