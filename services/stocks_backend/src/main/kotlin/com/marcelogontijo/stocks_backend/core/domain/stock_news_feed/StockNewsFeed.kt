package com.marcelogontijo.stocks_backend.core.domain.stock_news_feed

import java.time.Instant

data class StockNewsFeed(
    val title: String,
    val url: String,
    val summary: String,
    val timePublished: Instant,
    val source: String,
    val sourceDomain: String,
    val flags: Set<NewsFlag>,
)
