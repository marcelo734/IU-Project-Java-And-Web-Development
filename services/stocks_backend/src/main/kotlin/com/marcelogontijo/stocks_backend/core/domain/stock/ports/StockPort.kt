package com.marcelogontijo.stocks_backend.core.domain.stock.ports

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock

interface StockPort {
    suspend fun getStockBySymbol(symbol: String): Stock?
}
