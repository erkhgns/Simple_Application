package com.erkhgns.simpleapplication.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.erkhgns.simpleapplication.utils.Utils

open class BaseFragment: Fragment() {
     lateinit var viewModel: BaseViewModel

    fun initFragment(){
        observeSystemMessage()
    }


    private fun observeSystemMessage(){
        viewModel.getSystemMessage().observe(viewLifecycleOwner, Observer {
            Utils.showSystemMessageOnFragment(activity!!,it)
        })
    }
}