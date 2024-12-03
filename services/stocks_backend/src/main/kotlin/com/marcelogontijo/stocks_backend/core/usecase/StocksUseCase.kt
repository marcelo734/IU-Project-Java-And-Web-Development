package com.marcelogontijo.stocks_backend.core.usecase

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtility
import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtilityPort
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum
import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistory
import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistoryPort
import com.marcelogontijo.stocks_backend.core.domain.user.toUserStockSearchHistoryDomain
import kotlinx.coroutines.flow.toList
import java.sql.Timestamp
import java.time.Instant

class StocksUseCase(
    private val stockPort: StockPort,
    private val userStockSearchHistoryPort: UserStockSearchHistoryPort,
    private val stockSearchUtilityPort: StockSearchUtilityPort,
) {
    suspend fun getStocksBySymbol(
        symbol: String,
        frequency: TimeSeriesFrequenceEnum
    ): Stock? {
        return stockPort.getStockBySymbol(
            symbol = symbol,
            frequency = frequency,
        )?.also {
            if (it.symbol != null && it.overview != null) {
                userStockSearchHistoryPort
                    .saveHistory(
                        UserStockSearchHistory(
                            symbol = it.symbol,
                            name = it.overview.name,
                            date = Timestamp.from(Instant.now()).toString(),
                        )
                    )
            }
        }
    }

    suspend fun getUserStocksSearchHistory(): List<UserStockSearchHistory?> {
        return userStockSearchHistoryPort.getHistory().toList()
    }

    suspend fun searchStockBySymbol(symbol: String): List<StockSearchUtility> {
        return stockSearchUtilityPort.searchBySymbol(symbol)
            .also {
                userStockSearchHistoryPort
                    .saveHistory(
                        it.first()
                            .toUserStockSearchHistoryDomain()
                    )
            }
    }
}
