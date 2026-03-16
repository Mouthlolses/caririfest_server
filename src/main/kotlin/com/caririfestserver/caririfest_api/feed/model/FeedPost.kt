package com.caririfestserver.caririfest_api.feed.model

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "feeds_post",
    indexes = [
        Index(name = "idx_feed_created_at", columnList = "createdAt DESC") // No App - ORDER BY created_at DESC LIMIT 20
    ]
)
data class FeedPost(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: FeedType,

    @Column(nullable = false, length = 150)
    val title: String,

    @Column(columnDefinition = "TEXT")
    val content: String?,

    val imageUrl: String?,

    @Column(nullable = false)
    val createdAt: Instant = Instant.now(),

    val referenceId: Long?
)
