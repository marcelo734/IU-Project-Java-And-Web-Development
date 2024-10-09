package com.marcelogontijo.stocks_backend.core.domain.stock.ports

import com.marcelogontijo.stocks_backend.core.domain.stock.Stock
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum

interface StockPort {
    suspend fun getStockBySymbol(
        symbol: String,
        frequency: TimeSeriesFrequenceEnum = TimeSeriesFrequenceEnum.DAILY,
    ): Stock?
}
