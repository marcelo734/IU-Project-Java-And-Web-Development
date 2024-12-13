package com.marcelogontijo.stocks_backend.core.application.controller.stocks

import com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto.*
import com.marcelogontijo.stocks_backend.core.application.controller.stocks.exceptions.StockNotFoundException
import com.marcelogontijo.stocks_backend.core.domain.stock_time_series.enum.TimeSeriesFrequenceEnum
import com.marcelogontijo.stocks_backend.core.usecase.StocksUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/stocks")
@CrossOrigin(origins = ["*"])
@EnableCaching
class StocksController(
    @Autowired
    private val stocksUseCase: StocksUseCase,
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun getStocks(): UserStocksSearchHistoryDto {
        return UserStocksSearchHistoryDto(
            stocks = stocksUseCase.getUserStocksSearchHistory().mapNotNull { it?.toControllerDto() }
        )
    }

    @GetMapping("{symbol}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = ["stock_detail"], key = "#symbol")
    suspend fun getStockBySymbol(
        @PathVariable("symbol") symbol: String,
        @RequestParam("frequency") frequency: TimeSeriesFrequenceEnum = TimeSeriesFrequenceEnum.DAILY,
    ): StockDto {
        return stocksUseCase.getStocksBySymbol(
            symbol = symbol,
            frequency = frequency,
        )?.toControllerDto()
            ?: throw StockNotFoundException(symbol.uppercase())
    }
}
