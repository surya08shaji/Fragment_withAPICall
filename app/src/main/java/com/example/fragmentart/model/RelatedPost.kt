package com.example.fragmentart.model

data class RelatedPost(
    val audio: Any,
    val author: String,
    val category: List<CategoryXXX>,
    val content: String,
    val date: String,
    val favorite: Boolean,
    val id: Int,
    val image: String,
    val share_text: String,
    val title: String,
    val url: String
)