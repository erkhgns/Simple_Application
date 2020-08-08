package com.erkhgns.simpleapplication.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erkhgns.simpleapplication.base.BaseViewModel
import com.erkhgns.simpleapplication.database.entities.User
import com.erkhgns.simpleapplication.database.repositories.UserRepository
import com.erkhgns.simpleapplication.utils.ICountryPicker
import com.erkhgns.simpleapplication.utils.SystemMessage
import com.erkhgns.simpleapplication.utils.Utils
import com.hendraanggrian.appcompat.countrypicker.Country
import kotlinx.coroutines.launch

class LogInViewModel(application: Application) : BaseViewModel(application) {
    private val userRepository = UserRepository(application)
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val countryName = MutableLiveData<String>()

    private val userLogIn = MutableLiveData<User>()

    private lateinit var iCountryPicker: ICountryPicker

    init {
        countryName.value = Country.getDefault(application)!!.getName(application)
        userName.value ="admin"
        password.value ="admin"

    }

    fun onLogIn() {

        when {
            !isCompleteFields() -> systemMessage.value =
                SystemMessage(message = "Incomplete fields")
            else -> {
                viewModelScope.launch {
                    userRepository.selectSingleUser(userName.value!!, password.value!!).run {
                        if (this != null) {
                            systemMessage.postValue(SystemMessage(message = "Log in success"))
                            county_name = countryName.value!!
                            userLogIn.postValue(this)
                        } else {
                            systemMessage.postValue(SystemMessage(message = "Invalid Data"))
                        }

                    }
                }
            }
        }
    }

    fun getUserLogIn(): LiveData<User> = userLogIn

    fun initCountryPicker(iCountryPicker: ICountryPicker) {
        this.iCountryPicker = iCountryPicker
    }

    fun showCountryPicker() {
        iCountryPicker.showCountryPicker {
            countryName.value = it
        }
    }

    private fun isCompleteFields(): Boolean {
        return Utils.isCompleteFields(ArrayList<String?>().apply {
            add(userName.value)
            add(password.value)
        })
    }
}