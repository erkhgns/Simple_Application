package com.erkhgns.simpleapplication.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erkhgns.simpleapplication.base.BaseViewModel
import com.erkhgns.simpleapplication.network.User
import com.erkhgns.simpleapplication.network.WebServiceRepository
import com.erkhgns.simpleapplication.utils.SystemMessage
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val users = MutableLiveData<List<User>>()
    private val webServiceRepository = WebServiceRepository()

    init {
        getUsersFromNetwork()
    }

    private fun getUsersFromNetwork() {
        viewModelScope.launch {
            when (val userResponse = webServiceRepository.getUsers()) {
                is NetworkResponse.Success ->
                    users.postValue(userResponse.body)
                is NetworkResponse.NetworkError ->
                    userResponse.error.message?.run {
                        systemMessage.postValue(SystemMessage(message =this))
                    }
                is NetworkResponse.ServerError ->
                    userResponse.body?.run {
                        systemMessage.postValue(SystemMessage(message = this.error_message))
                    }
                is NetworkResponse.UnknownError ->
                    userResponse.error.message?.run {
                        systemMessage.postValue(SystemMessage(message =this))
                    }
                
            }


        }
    }

    fun getUsers(): LiveData<List<User>> = users
}