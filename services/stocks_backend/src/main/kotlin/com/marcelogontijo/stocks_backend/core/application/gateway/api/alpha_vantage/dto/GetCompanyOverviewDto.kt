package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.marcelogontijo.stocks_backend.core.domain.stock_overview.StockOverview

@JsonIgnoreProperties(ignoreUnknown = true)
data class GetCompanyOverviewDto(
    @JsonProperty("Name")
    val name: String,
    @JsonProperty("Description")
    val description: String,
    @JsonProperty("Country")
    val country: String,
    @JsonProperty("Sector")
    val sector: String,
    @JsonProperty("Industry")
    val industry: String,
    @JsonProperty("Currency")
    val currency: String,
    @JsonProperty("Exchange")
    val exchange: String,
)

fun GetCompanyOverviewDto.toDomain() = StockOverview(
    name = this.name,
    description = this.description,
    country = this.country,
    sector = this.sector,
    industry = this.industry,
    currency = this.currency,
    exchange = this.exchange,
)
