package com.marcelogontijo.stocks_backend.core.application.gateway.api.alpha_vantage.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.StockTimeSeries
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.StockTimeSeriesData
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.TimeSeriesMetadata
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
data class TimeSeriesDto(
    @JsonProperty("Meta Data")
    val metaData: TimeSeriesMetadataDto,
    @JsonProperty("Time Series (Daily)")
    val series: Map<String, TimeSeriesData>
) {
    fun toDomain(frequency: TimeSeriesFrequenceEnum): StockTimeSeries {
        return StockTimeSeries(
            metaData = TimeSeriesMetadata(
                frequency = frequency,
            ),
            series = this.series.mapValues {
                StockTimeSeriesData(
                    open = it.value.open,
                    high = it.value.high,
                    low = it.value.low,
                    volume = it.value.volume,
                    close = it.value.close,
                )
            }
        )
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class TimeSeriesMetadataDto(
    @JsonProperty("3. Last Refreshed")
    val lastRefreshed: LocalDate,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TimeSeriesData(
    @JsonProperty("1. open")
    val open: Float,
    @JsonProperty("2. high")
    val high: Float,
    @JsonProperty("3. low")
    val low: Float,
    @JsonProperty("4. close")
    val close: Float,
    @JsonProperty("5. volume")
    val volume: Int,
)
