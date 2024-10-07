package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage

import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.GetCompanyOverviewDto
import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.GetNewsSentimentDto
import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.GetStockQuoteResponseDto
import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.TimeSeriesDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Mono

@HttpExchange(accept = ["application/json"], contentType = "application/json")
interface AlphaVantageHttpClient {
    @GetExchange("/query?symbol={symbol}&function=GLOBAL_QUOTE")
    fun getGlobalQuote(
        @PathVariable("symbol") symbol: String,
    ): Mono<GetStockQuoteResponseDto>

    @GetExchange("/query?symbol={symbol}&function=OVERVIEW")
    fun getCompanyOverview(
        @PathVariable("symbol") symbol: String,
    ): Mono<GetCompanyOverviewDto>

    @GetExchange("/query?symbol={symbol}&function=NEWS_SENTIMENT")
    fun getNewsSentiment(
        @PathVariable("symbol") symbol: String,
    ): Mono<GetNewsSentimentDto>

    @GetExchange("/query?symbol={symbol}&function=TIME_SERIES_DAILY")
    fun getTimeSeriesDaily(
        @PathVariable("symbol") symbol: String,
    ): Mono<TimeSeriesDto>
}
