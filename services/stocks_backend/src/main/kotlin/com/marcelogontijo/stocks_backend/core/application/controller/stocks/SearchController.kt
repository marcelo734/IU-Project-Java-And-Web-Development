package com.marcelogontijo.stocks_backend.core.application.controller.stocks

import com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto.StockSearchUtilityDto
import com.marcelogontijo.stocks_backend.core.application.controller.stocks.dto.toControlerDto
import com.marcelogontijo.stocks_backend.core.usecase.StocksUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/search")
@CrossOrigin(origins = ["*"])
class SearchController(
    @Autowired
    private val stocksUseCase: StocksUseCase,
) {
    @GetMapping("stock")
    @ResponseStatus(HttpStatus.OK)
    suspend fun searchStock(
        @RequestParam("q") query: String,
    ): List<StockSearchUtilityDto> {
        return stocksUseCase.searchStockBySymbol(query)
            .map{ it.toControlerDto() }
    }
}