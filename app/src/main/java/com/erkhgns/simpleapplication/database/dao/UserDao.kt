package com.erkhgns.simpleapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.erkhgns.simpleapplication.database.entities.User


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM USERS WHERE user_name = :userName and password = :password")
    suspend fun selectSingleUser(userName: String , password: String): User?
}