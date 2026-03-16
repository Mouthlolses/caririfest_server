package com.caririfestserver.caririfest_api.feed.mapper

import com.caririfestserver.caririfest_api.feed.dto.PostResponse
import com.caririfestserver.caririfest_api.feed.model.FeedPost


fun FeedPost.toResponse(): PostResponse {
    return PostResponse(
        id = requireNotNull(this.id),
        title = this.title,
        type = this.type,
        content = this.content,
        imageUrl = this.imageUrl,
        createdAt = this.createdAt
    )
}