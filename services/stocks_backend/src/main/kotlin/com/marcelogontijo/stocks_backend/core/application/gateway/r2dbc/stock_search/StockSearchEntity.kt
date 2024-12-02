package com.marcelogontijo.stocks_backend.core.application.gateway.r2dbc.stock_search

import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistory
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("stocks_search_history")
data class StockSearchEntity(
    @Id
    @Column("id")
    val id: String,
    @Column("symbol")
    val symbol: String,
    @Column("date")
    val date: Timestamp,
) {
    fun toDomain(): UserStockSearchHistory {
        return UserStockSearchHistory(
            symbol = symbol,
            date = date.toString(),
        )
    }
}