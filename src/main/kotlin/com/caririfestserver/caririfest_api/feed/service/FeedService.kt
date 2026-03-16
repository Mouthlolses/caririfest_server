package com.caririfestserver.caririfest_api.feed.service

import com.caririfestserver.caririfest_api.feed.dto.CreatePostRequest
import com.caririfestserver.caririfest_api.feed.dto.PostResponse
import com.caririfestserver.caririfest_api.feed.mapper.toEntity
import com.caririfestserver.caririfest_api.feed.mapper.toResponse
import com.caririfestserver.caririfest_api.feed.repository.FeedRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


interface FeedService {

    fun createPost(request: CreatePostRequest): PostResponse

    fun findById(id: Long?): PostResponse

    fun findAllPosts(pageable: Pageable): Page<PostResponse>
}


@Service
class FeedServiceImpl(
    private val feedRepository: FeedRepository
) : FeedService {


    override fun createPost(request: CreatePostRequest): PostResponse {

        val entity = request.toEntity()

        val saved = feedRepository.save(entity)

        return saved.toResponse()

    }

    override fun findById(id: Long?): PostResponse {
        return feedRepository.findById(id)
            ?.toResponse()
            ?: throw RuntimeException("Post not found")
    }

    override fun findAllPosts(pageable: Pageable): Page<PostResponse> {
        return feedRepository
            .findAll(pageable)
            .map { it.toResponse() }
    }


}