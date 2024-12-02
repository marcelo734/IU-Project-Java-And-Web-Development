package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtility

data class StockSearchUtilityDto(
    val symbol: String,
    val name: String,
    val matchScore: Float
)

fun StockSearchUtility.toControlerDto() = StockSearchUtilityDto(
    symbol = symbol,
    name = name,
    matchScore = matchScore.toFloat()
)