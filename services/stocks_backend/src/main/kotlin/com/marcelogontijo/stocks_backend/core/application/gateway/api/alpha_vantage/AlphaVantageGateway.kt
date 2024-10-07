package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage

import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.toDomain
import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class AlphaVantageGateway(
    private val alphaVantageHttpClient: AlphaVantageHttpClient,
): StockPort {
    override suspend fun getStockBySymbol(symbol: String): Stock? = coroutineScope {
        val globalQuoteAsync = async {
            alphaVantageHttpClient.getGlobalQuote(symbol).awaitSingle()
        }

        val globalQuote = globalQuoteAsync.await().globalQuote

        if (globalQuote == null) {
            return@coroutineScope null
        }

        return@coroutineScope Stock(
            symbol = symbol,
            globalQuote = globalQuote.toDomain()
        )
    }

}
