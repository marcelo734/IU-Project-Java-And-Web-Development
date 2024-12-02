package com.marcelogontijo.stocks_backend.core.usecase

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum
import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistory
import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistoryPort
import kotlinx.coroutines.flow.toList

class StocksUseCase(
    private val stockPort: StockPort,
    private val userStockSearchHistoryPort: UserStockSearchHistoryPort,
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

    suspend fun getUserStocksSearchHistory(): List<UserStockSearchHistory?> {
        return userStockSearchHistoryPort.getHistory().toList()
    }
}
