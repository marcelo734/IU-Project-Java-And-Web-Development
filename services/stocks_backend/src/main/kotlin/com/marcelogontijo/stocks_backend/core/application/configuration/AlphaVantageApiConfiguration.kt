package com.marcelogontijo.stocks_backend.core.application.configuration

import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.AlphaVantageHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class AlphaVantageApiConfiguration(
    private val alphaVantageApiKeyInterceptor: AlphaVantageApiKeyInterceptor
) {
    @Value("\${alpha-vantage.base-url}")
    private lateinit var alphaVantageBaseUrl: String

    @Bean
    fun alphaVantageHttpClient(): AlphaVantageHttpClient {
        val webClient = WebClient.builder()
            .baseUrl(alphaVantageBaseUrl)
            .filter(alphaVantageApiKeyInterceptor)
            .build()

        val httpServiceProxyFactory = HttpServiceProxyFactory
            .builderFor(
                WebClientAdapter.create(webClient)
            )
            .build()

        return httpServiceProxyFactory.createClient(AlphaVantageHttpClient::class.java)
    }
}
