package com.marcelogontijo.stocks_backend.core.domain.stock.search_utility

interface StockSearchUtilityPort {
    suspend fun searchBySymbol(symbol: String,): List<StockSearchUtility>
}