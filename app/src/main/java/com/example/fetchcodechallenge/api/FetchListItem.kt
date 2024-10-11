package com.example.fetchcodechallenge.api

import com.squareup.moshi.Json

typealias ItemId = Int
typealias GroupId = Int

data class FetchListItem(
    @Json(name = "id") val id: ItemId,
    @Json(name = "listId") val listId: GroupId,
    @Json(name = "name") val name: String?
)
