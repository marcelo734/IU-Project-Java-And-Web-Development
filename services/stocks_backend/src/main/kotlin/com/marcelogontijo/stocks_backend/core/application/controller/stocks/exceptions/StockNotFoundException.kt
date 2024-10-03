package com.marcelogontijo.stocks_backend.core.application.controller.stocks.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class StockNotFoundException(symbol: String)
    : RuntimeException("Stock $symbol not found")
