package com.example.fragmentart.model

data class CategoryX(
    val count: Int,
    val description: String,
    val filter: String,
    val icon: String,
    val name: String,
    val parent: String,
    val slug: String,
    val taxonomy: String,
    val term_group: Int,
    val term_id: Int,
    val term_taxonomy_id: Int
)