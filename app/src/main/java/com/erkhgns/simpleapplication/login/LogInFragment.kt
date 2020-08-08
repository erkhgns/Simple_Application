package com.erkhgns.simpleapplication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.erkhgns.simpleapplication.base.BaseFragment
import com.erkhgns.simpleapplication.database.AppDatabase
import com.erkhgns.simpleapplication.database.entities.User
import com.erkhgns.simpleapplication.databinding.FragmentLogInBinding
import com.erkhgns.simpleapplication.utils.ICountryPicker
import com.hendraanggrian.appcompat.countrypicker.Country
import com.hendraanggrian.appcompat.countrypicker.CountryPickerDialog

class LogInFragment : BaseFragment(), ICountryPicker {
    private lateinit var binding: FragmentLogInBinding

    private val logInViewModel: LogInViewModel by lazy {
        viewModel as LogInViewModel
    }
    private lateinit var onLogIn: (User) -> Unit

    companion object {
        fun newInstance(onLogIn: (User) -> Unit): LogInFragment = LogInFragment().apply {
            this.onLogIn = onLogIn
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this)[LogInViewModel::class.java]
        binding.viewmodel = logInViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initFragment()
        logInViewModel.initCountryPicker(this)
        observeUserLogIn()
    }

    private fun observeUserLogIn() {
        logInViewModel.getUserLogIn().observe(viewLifecycleOwner, Observer {
            onLogIn(it)
        })
    }

    override fun showCountryPicker(countryName: (String) -> Unit) {
        CountryPickerDialog.Builder(context!!).setOnSelectedListener {
            countryName(it.getName(context!!))
        }.show()
    }


}