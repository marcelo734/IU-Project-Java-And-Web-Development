package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtility

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetSearchUtilityResponseDto(
    @JsonProperty("bestMatches")
    val bestMatches: List<SearchUtilityDto?>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchUtilityDto(
    @JsonProperty("1. symbol")
    val symbol: String,
    @JsonProperty("2. name")
    val name: String,
    @JsonProperty("4. region")
    val region: String,
    @JsonProperty("8. currency")
    val currency: String,
    @JsonProperty("9. matchScore")
    val matchScore: String,
) {
    fun toStockSearchUtilityDomain() = StockSearchUtility(
        symbol = symbol,
        name = name,
        region = region,
        currency = currency,
        matchScore = matchScore,
    )
}