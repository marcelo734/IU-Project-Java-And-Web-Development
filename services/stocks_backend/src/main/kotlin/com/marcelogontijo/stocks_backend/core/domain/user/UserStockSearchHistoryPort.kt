package com.marcelogontijo.stocks_backend.core.domain.user

import kotlinx.coroutines.flow.Flow

interface UserStockSearchHistoryPort {
    suspend fun getHistory(): Flow<UserStockSearchHistory?>

    suspend fun saveHistory(stock: UserStockSearchHistory): UserStockSearchHistory
}