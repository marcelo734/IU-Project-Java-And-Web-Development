package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import com.marcelogontijo.stocks_backend.core.domain.stock_global_quote.StockGlobalQuote
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AlphaVantageGateway(
    private val alphaVantageHttpClient: AlphaVantageHttpClient,
): StockPort {
    override suspend fun getStockBySymbol(symbol: String): Stock? {
        val globalQuoteAsync = alphaVantageHttpClient.getGlobalQuote(symbol).awaitSingle().globalQuote

        return Stock(
            symbol = symbol,
            globalQuote =  StockGlobalQuote(
                price = globalQuoteAsync.price,
                latestTradingDay = Instant.from(globalQuoteAsync.latestTradingDay),
                previousClose = globalQuoteAsync.previousClose,
                change = globalQuoteAsync.change,
                changePercent = globalQuoteAsync.changePercent,
                open = globalQuoteAsync.open,
                high = globalQuoteAsync.high,
                low = globalQuoteAsync.low,
                volume = globalQuoteAsync.volume,
            ),
        )
    }

}
