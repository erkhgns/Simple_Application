package com.erkhgns.simpleapplication.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erkhgns.simpleapplication.utils.SystemMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val job = Job()
    val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    val systemMessage = MutableLiveData<SystemMessage>()


    fun getSystemMessage(): LiveData<SystemMessage> = systemMessage
}