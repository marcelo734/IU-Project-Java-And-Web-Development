package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.stock_overview.StockOverview

data class StockOverviewDto(
    val name: String,
    val description: String,
    val country: String,
    val sector: String,
    val industry: String,
    val currency: String,
    val exchange: String,
)

fun StockOverview.toDto() = StockOverviewDto(
    name = this.name,
    description = this.description,
    country = this.country,
    sector = this.sector,
    industry = this.industry,
    currency = this.currency,
    exchange = this.exchange,
)
