package com.example.findissues.models.home

data class User(
    val login: String = "",
    val avatar_url: String = "",
    val name: String = "",
    val bio: String = "",
    val company: String = "",
    val location: String = "",
    val twitter_username: String = "",
    val followers: Int = 0,
    val following: Int = 0
)
