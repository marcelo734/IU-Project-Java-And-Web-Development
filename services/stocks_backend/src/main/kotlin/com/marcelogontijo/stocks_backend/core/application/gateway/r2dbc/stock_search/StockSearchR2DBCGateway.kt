package com.marcelogontijo.stocks_backend.core.application.gateway.r2dbc.stock_search

import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistory
import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistoryPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.stereotype.Service

@Service
class StockSearchR2DBCGateway(
    private val stockSearchRepository: StockSearchRepository
): UserStockSearchHistoryPort {
    override suspend fun getHistory(): Flow<UserStockSearchHistory?> {
        return stockSearchRepository.findAll().map { it.toDomain() }
    }

    override suspend fun saveHistory(stock: UserStockSearchHistory): UserStockSearchHistory {
        return stockSearchRepository
            .save(stock.toEntity())
            .toDomain()
    }
}