package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import java.time.Instant

data class StockDto(
    val symbol: String?,
    val overview: StockOverviewDto?,
    val newsFeed: List<StockNewsFeedDto>? = emptyList(),
    val timeSeries: StockTimeSeriesDto?,
    val globalQuote: StockGlobalQuoteDto?,
    val addedAt: Instant? = Instant.now(),
)

fun Stock.toStockDto() = StockDto(
    symbol = this.symbol,
    overview = this.overview?.toDto(),
    newsFeed = this.newsFeed?.map { it.toDto() },
    timeSeries = this.timeSeries?.toDto(),
    globalQuote = this.globalQuote?.toDto(),
    addedAt = this.addedAt,
)
