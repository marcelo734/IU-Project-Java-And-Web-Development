export type TimeSeries = {
    [date: string]: BasicFinancialInfo
}

export type BasicFinancialInfo = {
    open: string;
    high: string;
    low: string;
    volume: string;
}

export type GlobalQuote = BasicFinancialInfo & {
    price: string;
    latestTradingDay: string;
    previousClose: string;
    change: string;
    changePercent: string;
}

export type Stock = {
    symbol: string;
    timeSeries?: TimeSeries[];
    globalQuote?: GlobalQuote;
    addedAt?: string
}
