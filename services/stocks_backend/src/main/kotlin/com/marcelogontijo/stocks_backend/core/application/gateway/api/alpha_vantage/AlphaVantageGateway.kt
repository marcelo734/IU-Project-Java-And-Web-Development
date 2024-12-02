package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage

import com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto.toDomain
import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtility
import com.marcelogontijo.stocks_backend.core.domain.stock.search_utility.StockSearchUtilityPort
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AlphaVantageGateway(
    private val alphaVantageHttpClient: AlphaVantageHttpClient,
): StockPort, StockSearchUtilityPort {
    override suspend fun getStockBySymbol(
        symbol: String,
        frequency: TimeSeriesFrequenceEnum,
    ): Stock? = coroutineScope {
        val globalQuoteAsync = async {
            alphaVantageHttpClient.getGlobalQuote(symbol).awaitSingle()
        }

        val companyOverviewAsync = async {
            alphaVantageHttpClient.getCompanyOverview(symbol).awaitSingle()
        }

        val newsFeedAsync = async {
            alphaVantageHttpClient.getNewsSentiment(symbol).awaitSingle()
        }

        val dailyTimeSeries = async {
            alphaVantageHttpClient.getTimeSeriesDaily(symbol).awaitSingle()
        }

        val globalQuote = globalQuoteAsync.await().globalQuote
        val companyOverview = companyOverviewAsync.await()
        val newsFeed = newsFeedAsync.await()
        val timeSeries = dailyTimeSeries.await()

        return@coroutineScope Stock(
            symbol = symbol,
            overview = companyOverview.toDomain(),
            globalQuote = globalQuote?.toDomain(),
            newsFeed = newsFeed.feed.map { it.toDomain() },
            timeSeries = timeSeries.toDomain(frequency),
            addedAt = Instant.now(),
        )
    }

    override suspend fun searchBySymbol(symbol: String): List<StockSearchUtility> {
        return alphaVantageHttpClient.searchStockBySymbol(symbol)
            .awaitFirst()
            .bestMatches
            .mapNotNull { it?.toStockSearchUtilityDomain() }
    }

}
