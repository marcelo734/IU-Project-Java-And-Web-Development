package com.marcelogontijo.stocks_backend.core.application.gateway.r2dbc.stock_search

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StockSearchRepository : CoroutineCrudRepository<StockSearchEntity, String> {
    suspend fun findBySymbol(symbol: String): StockSearchEntity?
}