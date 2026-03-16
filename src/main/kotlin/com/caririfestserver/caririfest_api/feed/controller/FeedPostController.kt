package com.caririfestserver.caririfest_api.feed.controller

import com.caririfestserver.caririfest_api.feed.dto.CreatePostRequest
import com.caririfestserver.caririfest_api.feed.dto.PostResponse
import com.caririfestserver.caririfest_api.feed.service.FeedService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feeds")
class FeedPostController(
    private val feedService: FeedService
) {

    @PostMapping
    @Operation(summary = "Criar post")
    fun createPost(
        @RequestBody request: CreatePostRequest
    ): PostResponse {
        return feedService.createPost(request)
    }

    @GetMapping
    @Operation(summary = "Buscar eventos")
    fun findAll(pageable: Pageable): Page<PostResponse> {
        return feedService.findAllPosts(pageable)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento esperado")
    fun findById(@PathVariable id: Long): PostResponse {
        return feedService.findById(id)
    }

}