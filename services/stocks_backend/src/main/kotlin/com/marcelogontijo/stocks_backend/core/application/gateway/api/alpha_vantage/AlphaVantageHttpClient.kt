package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage

import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.GetStockQuoteResponseDto
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Mono

@HttpExchange(accept = ["application/json"], contentType = "application/json")
interface AlphaVantageHttpClient {
    @GetExchange("/query?function=GLOBAL_QUOTE&symbol={symbol}")
    fun getGlobalQuote(
        @PathVariable("symbol") symbol: String,
    ): Mono<GetStockQuoteResponseDto>
}
