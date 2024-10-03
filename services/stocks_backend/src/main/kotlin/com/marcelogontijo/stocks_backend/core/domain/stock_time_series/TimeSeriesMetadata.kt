package com.marcelogontijo.stocks_backend.core.domain.stock_time_series

import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum

data class TimeSeriesMetadata(
    val frequency: TimeSeriesFrequenceEnum,
)
