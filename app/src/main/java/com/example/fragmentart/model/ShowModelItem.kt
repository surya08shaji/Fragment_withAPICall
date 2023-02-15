package com.example.fragmentart.model

data class ShowModelItem(
    val audio: Any,
    val author: String,
    val category: List<CategoryXX>,
    val content: String,
    val date: String,
    val hide_title: Boolean,
    val id: Int,
    val image: String,
    val magazin: List<MagazinX>,
    val related_post: List<RelatedPost>,
    val title: String,
    val url: String
)