package com.erkhgns.simpleapplication.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.erkhgns.simpleapplication.base.BaseFragment
import com.erkhgns.simpleapplication.databinding.FragmentHomeBinding
import com.erkhgns.simpleapplication.network.User
import com.erkhgns.simpleapplication.network.WebServiceRepository
import com.erkhgns.simpleapplication.userdetail.UserDetailFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var onclick: (User) -> Unit
    private lateinit var user: com.erkhgns.simpleapplication.database.entities.User
    private val homeViewModel: HomeViewModel by lazy {
        viewModel as HomeViewModel
    }

    companion object {
        fun newInstance(
            user: com.erkhgns.simpleapplication.database.entities.User,
            onclick: (User) -> Unit
        ): HomeFragment = HomeFragment().apply {
            this.onclick = onclick
            this.user = user
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.user = user
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]
        initFragment()
        observeUsers()
    }

    private fun observeUsers() {
        homeViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            val adapter = UserRecyclerAdapter(it, onclick)
            binding.rvUsers.layoutManager = LinearLayoutManager(context)
            binding.rvUsers.adapter = adapter
        })
    }

}