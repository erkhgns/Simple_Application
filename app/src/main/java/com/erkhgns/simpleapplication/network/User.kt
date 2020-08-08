package com.erkhgns.simpleapplication.network

class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {
    inner class Address(
        val street: String,
        val suite: String,
        val city: String,
        val zipcode: String,
        val geo: Geo
    ) {
        inner class Geo(val lat: String, val lng: String)
    }

    inner class Company(val name: String, val catchPhrase: String, val bs: String)
}