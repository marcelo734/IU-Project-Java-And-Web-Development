package com.marcelogontijo.stocks_backend.core.application.configuration

import com.marcelogontijo.stocks_backend.core.domain.stock.ports.StockPort
import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistoryPort
import com.marcelogontijo.stocks_backend.core.usecase.StocksUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {
    @Bean
    fun stocksUseCase(
        stockPort: StockPort,
        userStockSearchHistoryPort: UserStockSearchHistoryPort,
    ) = StocksUseCase(
        stockPort = stockPort,
        userStockSearchHistoryPort = userStockSearchHistoryPort,
    )
}
