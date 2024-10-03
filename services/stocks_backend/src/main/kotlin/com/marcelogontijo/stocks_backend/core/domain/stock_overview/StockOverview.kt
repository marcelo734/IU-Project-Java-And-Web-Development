package com.marcelogontijo.stocks_backend.core.domain.stock_overview

data class StockOverview(
    val name: String,
    val description: String,
    val country: String,
    val sector: String,
    val industry: String,
    val currency: String,
    val exchange: String,
)
