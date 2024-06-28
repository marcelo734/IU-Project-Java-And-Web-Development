export enum TimeSeriesFrequence {
    INTRADAY = "INTRADAY",
    DAILY = "DAILY",
    WEEKLY = "WEEKLY",
    MONTHLY = "MONTHLY",
}
export type TimeSeriesMetadata = {
    frequency: TimeSeriesFrequence
}

export type TimeSeriesData = {
    [date: string]: BasicFinancialInfo
}

export type TimeSeries = {
    metaData: TimeSeriesMetadata;
    series: TimeSeriesData
}

export type BasicFinancialInfo = {
    open: string;
    high: string;
    low: string;
    volume: string;
    close?: string;
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
    timeSeries?: TimeSeries;
    globalQuote?: GlobalQuote;
    addedAt?: string
}
