package com.caririfestserver.caririfest_api.feed.repository

import com.caririfestserver.caririfest_api.feed.model.FeedPost
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedRepository : JpaRepository<FeedPost, Long> {

    fun findById(id: Long?): FeedPost?

}