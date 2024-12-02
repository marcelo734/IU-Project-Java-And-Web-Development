package com.marcelogontijo.stocks_backend.core.domain.stock.search_utility

data class StockSearchUtility(
    val symbol: String,
    val name: String,
    val region: String,
    val currency: String,
    val matchScore: String,
)