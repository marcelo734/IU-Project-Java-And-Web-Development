package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.marcelogontijo.stocks_backend.core.domain.stock_news_feed.NewsFlag
import com.marcelogontijo.stocks_backend.core.domain.stock_news_feed.StockNewsFeed
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetNewsSentimentDto(
    @JsonProperty("feed")
    val feed: List<FeedDto>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeedDto(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("summary")
    val summary: String,
    @JsonProperty("time_published")
    val timePublished: String,
    @JsonProperty("source")
    val source: String,
    @JsonProperty("source_domain")
    val sourceDomain: String,
    @JsonProperty("topics")
    val topics: List<FeedTopicsDto>,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class FeedTopicsDto(
    @JsonProperty("topic")
    val topic: String,
    @JsonProperty("relevance_score")
    val relevanceScore: Float,
)

fun FeedDto.toDomain() = StockNewsFeed(
    title = this.title,
    url = this.url,
    summary = this.summary,
    timePublished = null, // todo: add logic to convert string "20241007T230755" to LocalDateTime
    source = this.source,
    sourceDomain = this.sourceDomain,
    flags = this.topics.map {
        it.toDomain()
    }.toSet()
)

fun FeedTopicsDto.toDomain() = NewsFlag(
    name = this.topic,
    relevance = this.relevanceScore,
)
