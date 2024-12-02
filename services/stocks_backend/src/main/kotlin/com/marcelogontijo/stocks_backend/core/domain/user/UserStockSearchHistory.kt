package com.marcelogontijo.stocks_backend.core.domain.user

data class UserStockSearchHistory(
    val symbol: String,
    val date: String,
)