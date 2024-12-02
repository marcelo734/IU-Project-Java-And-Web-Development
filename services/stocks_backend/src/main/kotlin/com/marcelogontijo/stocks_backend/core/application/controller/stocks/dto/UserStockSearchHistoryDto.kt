package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistory

data class UserStocksSearchHistoryDto(
    val stocks: List<StockSearchHistory>
)

data class StockSearchHistory(
    val symbol: String,
    val date: String,
)

fun UserStockSearchHistory.toControllerDto() = StockSearchHistory(
    symbol = symbol,
    date = date
)