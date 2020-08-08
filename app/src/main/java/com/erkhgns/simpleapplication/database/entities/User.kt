package com.erkhgns.simpleapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class User(
    @PrimaryKey
    val user_name: String,
    val password: String,
    val first_name: String,
    val last_name: String,
    var county_name: String
) {
}