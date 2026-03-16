package com.caririfestserver.caririfest_api.feed.dto

import com.caririfestserver.caririfest_api.feed.model.FeedType

data class CreatePostRequest(
    val type: FeedType,
    val title: String,
    val content: String?,
    val description: String?,
    val imageUrl: String?,
    val referenceId: Long?
)