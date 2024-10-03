package com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto

import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.StockTimeSeries
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.StockTimeSeriesData
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.TimeSeriesMetadata
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum

data class StockTimeSeriesDto (
    val metaData: TimeSeriesMetadataDto,
    val series: Map<String, SeriesDataDto>
)

data class TimeSeriesMetadataDto(
    val frequency: TimeSeriesFrequenceEnum,
)

data class SeriesDataDto(
    val open: Float,
    val high: Float,
    val low: Float,
    val volume: Float,
    val close: Float? = null,
)

fun StockTimeSeries.toDto() = StockTimeSeriesDto(
    metaData = this.metaData.toDto(),
    series = this.series.mapValues { it.value.toDto() }
)

fun TimeSeriesMetadata.toDto() = TimeSeriesMetadataDto(
    frequency = this.frequency
)

fun StockTimeSeriesData.toDto() = SeriesDataDto(
    open = this.open,
    high = this.high,
    low = this.low,
    volume = this.volume,
    close = this.close,
)
