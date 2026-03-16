package com.caririfestserver.caririfest_api.feed.mapper

import com.caririfestserver.caririfest_api.feed.dto.CreatePostRequest
import com.caririfestserver.caririfest_api.feed.model.FeedPost

fun CreatePostRequest.toEntity(): FeedPost {
    return FeedPost(
        type = this.type,
        title = this.title,
        content = this.content,
        imageUrl = this.imageUrl,
        referenceId = this.referenceId
    )
}