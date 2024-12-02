package com.marcelogontijo.stocks_backend.core.domain.user

import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtility
import java.sql.Timestamp
import java.time.Instant

data class UserStockSearchHistory(
    val symbol: String,
    val name: String,
    val date: String,
)

fun StockSearchUtility.toUserStockSearchHistoryDomain() = UserStockSearchHistory(
    symbol = symbol,
    name = name,
    date = Timestamp.from(Instant.now()).toString(),
)