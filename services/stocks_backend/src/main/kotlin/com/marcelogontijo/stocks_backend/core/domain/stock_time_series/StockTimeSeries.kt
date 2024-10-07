package com.marcelogontijo.stocks_backend.core.domain.stock_time_series

data class StockTimeSeries(
    val metaData: TimeSeriesMetadata,
    val series: Map<String, StockTimeSeriesData>
)


data class StockTimeSeriesData(
    val open: Float,
    val high: Float,
    val low: Float,
    val volume: Int,
    val close: Float? = null
)
