package com.marcelogontijo.stocks_backend.core.domain.stock_global_quote

import java.time.Instant

data class StockGlobalQuote(
    val price: Float,
    val latestTradingDay: Instant,
    val previousClose: Float,
    val change: Float,
    val changePercent: Float,
    val open: Float,
    val high: Float,
    val low: Float,
    val volume: Int,
    val close: Float? = null
)
