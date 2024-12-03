export type UserStocksSearchHistory = {
    symbol: string,
    name: string,
    date: Date,
}

export type ApiGetUserStocksSearchHistory = {
    stocks: UserStocksSearchHistory[]
}