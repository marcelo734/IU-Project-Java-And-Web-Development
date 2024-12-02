package com.marcelogontijo.stocks_backend.core.application.gateway.r2dbc.stock_search

import com.marcelogontijo.stocks_backend.core.domain.user.UserStockSearchHistory
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp
import java.time.Instant

@Table("stocks_search_history")
data class StockSearchEntity(
    @Id
    @Column("id")
    val id: String? = null,
    @Column("symbol")
    val symbol: String,
    @Column("name")
    val name: String,
    @Column("date")
    val date: Timestamp = Timestamp.from(Instant.now()),
) {
    fun toDomain(): UserStockSearchHistory {
        return UserStockSearchHistory(
            symbol = symbol,
            name = name,
            date = date.toString(),
        )
    }
}

fun UserStockSearchHistory.toEntity() = StockSearchEntity(
    symbol = symbol,
    name = name,
)