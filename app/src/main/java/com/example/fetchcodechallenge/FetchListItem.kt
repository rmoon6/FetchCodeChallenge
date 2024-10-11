package com.example.fetchcodechallenge

import com.squareup.moshi.Json

data class FetchListItem(
    @Json(name = "id") val id: Int,
    @Json(name = "listId") val listId: Int,
    @Json(name = "name") val name: String?
)
