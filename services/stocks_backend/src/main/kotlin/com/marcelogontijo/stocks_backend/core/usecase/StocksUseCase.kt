package com.marcelogontijo.stocks_backend.core.usecase

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort

class StocksUseCase(
    private val stockPort: StockPort
) {
    suspend fun getStocksBySymbol(symbol: String): Stock? {
        return stockPort.getStockBySymbol(symbol)
    }
}
