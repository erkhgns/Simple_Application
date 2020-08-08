package com.erkhgns.simpleapplication.database.repositories

import android.content.Context
import com.erkhgns.simpleapplication.database.AppDatabase
import com.erkhgns.simpleapplication.database.dao.UserDao
import com.erkhgns.simpleapplication.database.entities.User

class UserRepository(
    private val context: Context,
    private val userDao:UserDao = AppDatabase.getInstance(context).userDao()


) {
    suspend fun selectSingleUser(userName: String, password: String): User? =
        userDao.selectSingleUser(userName, password)

}