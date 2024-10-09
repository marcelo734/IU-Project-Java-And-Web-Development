package com.marcelogontijo.stocks_backend.core.usecase

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum

class StocksUseCase(
    private val stockPort: StockPort
) {
    suspend fun getStocksBySymbol(
        symbol: String,
        frequency: TimeSeriesFrequenceEnum
    ): Stock? {
        return stockPort.getStockBySymbol(
            symbol = symbol,
            frequency = frequency,
        )
    }
}
