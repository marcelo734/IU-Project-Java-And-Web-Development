package com.marcelogontijo.stocks_backend.core.application.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class WebClientConfig(
    private val alphaVantageApiKeyInterceptor: AlphaVantageApiKeyInterceptor,
) {
    @Value("\${alpha-vantage.base-url}")
    private lateinit var alphaVantageBaseUrl: String

    @Bean
    fun alphaVantageWebClient() = WebClient.builder()
        .baseUrl(alphaVantageBaseUrl)
        .exchangeStrategies(
            ExchangeStrategies
                .builder()
                .codecs(this::acceptedCodecs)
                .build())
        .filter(alphaVantageApiKeyInterceptor)
        .build()

   private fun acceptedCodecs(clientCodecConfigurer: ClientCodecConfigurer) {
       clientCodecConfigurer.customCodecs().encoder(Jackson2JsonEncoder(ObjectMapper(), TEXT_HTML))
       clientCodecConfigurer.customCodecs().decoder(Jackson2JsonDecoder(ObjectMapper(), TEXT_HTML))
   }
}

