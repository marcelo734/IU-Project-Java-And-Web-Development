package com.marcelogontijo.stocks_backend.core.domain.stock_global_quote

import java.time.LocalDate

data class StockGlobalQuote(
    val price: Float,
    val latestTradingDay: LocalDate,
    val previousClose: Float,
    val change: Float,
    val changePercent: String,
    val open: Float,
    val high: Float,
    val low: Float,
    val volume: Int,
)
