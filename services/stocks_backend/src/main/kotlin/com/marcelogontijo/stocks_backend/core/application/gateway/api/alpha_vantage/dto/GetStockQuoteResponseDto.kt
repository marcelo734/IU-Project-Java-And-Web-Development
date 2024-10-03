package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class GetStockQuoteResponseDto(
    @JsonProperty("Global Quote")
    val globalQuote: GlobalQuoteDto
)

data class GlobalQuoteDto(
    @JsonProperty("01. symbol")
    val symbol: String,
    @JsonProperty("02. open")
    val open: Float,
    @JsonProperty("03. high")
    val high: Float,
    @JsonProperty("04. low")
    val low: Float,
    @JsonProperty("05. price")
    val price: Float,
    @JsonProperty("06. volume")
    val volume: Int,
    @JsonProperty("07. latest trading day")
    val latestTradingDay: LocalDate,
    @JsonProperty("08. previous close")
    val previousClose: Float,
    @JsonProperty("09. change")
    val change: Float,
    @JsonProperty("10. change percent")
    val changePercent: Float,
)
