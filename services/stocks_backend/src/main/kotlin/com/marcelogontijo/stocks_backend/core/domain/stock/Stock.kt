package com.marcelogontijo.stocks_backend.core.domain.stock

import com.marcelogontijo.stocks_backend.core.domain.stock_global_quote.StockGlobalQuote
import com.marcelogontijo.stocks_backend.core.domain.stock_news_feed.StockNewsFeed
import com.marcelogontijo.stocks_backend.core.domain.stock_overview.StockOverview
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.StockTimeSeries
import java.time.Instant

data class Stock(
    val symbol: String? = null,
    val overview: StockOverview? = null,
    val newsFeed: List<StockNewsFeed>? = emptyList(),
    val timeSeries: StockTimeSeries? = null,
    val globalQuote: StockGlobalQuote? = null,
    val addedAt: Instant? = Instant.now(),
)
