package com.marcelogontijo.stocks_backend.core.application.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.ExchangeFunction
import reactor.core.publisher.Mono
import java.net.URI

@Service
class AlphaVantageApiKeyInterceptor: ExchangeFilterFunction {
    @Value("\${alpha-vantage.api-key}")
    private lateinit var alphaVantageApiKey: String

    override fun filter(request: ClientRequest, next: ExchangeFunction): Mono<ClientResponse> {
        val reqUri = request.url()
        val newUri = URI(reqUri.getScheme()
            , reqUri.getAuthority(), reqUri.getPath()
            , reqUri.getQuery() + "&apiKey=${alphaVantageApiKey}"
            , null)
        val filtered = ClientRequest.from(request)
            .url(newUri)
            .header(HttpHeaders.ACCEPT, "application/json")
            .build()
        return next.exchange(filtered)
    }
}
