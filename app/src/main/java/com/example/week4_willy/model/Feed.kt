package com.example.week4_willy.model

data class Feed(
    val username : String,
    val profilePicture : String,
    val feedContent : String,
    val isLike : Boolean,
    val isSaved : Boolean,
    val like : Int,
    val caption : String,
    val date: String
)