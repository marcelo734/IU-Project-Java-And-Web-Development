package com.marcelogontijo.stocks_backend.core.application.controller.stocks

import com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto.StockDto
import com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto.toStockDto
import com.marcelogontijo.stocks_backend.core.application.controller.stocks.exceptions.StockNotFoundException
import com.marcelogontijo.stocks_backend.core.usecase.StocksUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/stocks")
class StocksController(
    @Autowired
    private val stocksUseCase: StocksUseCase,
) {
    @GetMapping("{symbol}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun getStockBySymbol(
        @PathVariable("symbol") symbol: String
    ): StockDto {
        return stocksUseCase.getStocksBySymbol(symbol)?.toStockDto()
            ?: throw StockNotFoundException(symbol)
    }
}
