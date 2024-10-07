package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.stock_news_feed.NewsFlag
import com.marcelogontijo.stocks_backend.core.domain.stock_news_feed.StockNewsFeed
import java.time.LocalDateTime

data class StockNewsFeedDto(
    val title: String,
    val url: String,
    val summary: String,
    val timePublished: LocalDateTime?,
    val source: String,
    val sourceDomain: String,
    val flags: Set<StockNewsFlagDto>,
)

data class StockNewsFlagDto(
    val name: String,
    val relevance: Float,
)


fun StockNewsFeed.toDto() = StockNewsFeedDto(
    title = this.title,
    url = this.url,
    summary = this.summary,
    timePublished = this.timePublished,
    source = this.source,
    sourceDomain = this.sourceDomain,
    flags = this.flags.map { it.toDto() }.toSet()
)

fun NewsFlag.toDto() = StockNewsFlagDto(
    name = this.name,
    relevance = this.relevance,
)
