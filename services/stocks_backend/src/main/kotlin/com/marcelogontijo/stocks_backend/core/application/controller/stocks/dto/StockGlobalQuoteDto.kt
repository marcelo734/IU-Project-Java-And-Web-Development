package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.stock_global_quote.StockGlobalQuote
import java.time.LocalDate

data class StockGlobalQuoteDto(
    val price: Float,
    val latestTradingDay: LocalDate,
    val previousClose: Float,
    val change: Float,
    val changePercent: String,
    val open: Float,
    val high: Float,
    val low: Float,
    val volume: Int,
    val close: Float? = null,
)

fun StockGlobalQuote.toDto() = StockGlobalQuoteDto(
    price = this.price,
    latestTradingDay = this.latestTradingDay,
    previousClose = this.previousClose,
    change = this.change,
    changePercent = this.changePercent,
    open = this.open,
    high = this.high,
    low = this.low,
    volume = this.volume
)
