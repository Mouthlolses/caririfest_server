package com.caririfestserver.caririfest_api.feed.dto

import com.caririfestserver.caririfest_api.feed.model.FeedType
import java.time.Instant

data class PostResponse(
    val id: Long,
    val title: String,
    val type: FeedType,
    val content: String?,
    val imageUrl: String?,
    val createdAt: Instant,
)
