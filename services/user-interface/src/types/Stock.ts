import { Moment } from "moment"

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
    open: number;
    high: number;
    low: number;
    volume: number;
    close?: number;
}

export type GlobalQuote = BasicFinancialInfo & {
    price: number;
    latestTradingDay: Moment;
    previousClose: number;
    change: number;
    changePercent: string;
}

export type StockOverview = {
    name: string;
    description: string;
    country: string;
    sector: string;
    industry: string;
    exchange: string;
    currency: string;
}

export type Stock = {
    symbol: string;
    overview: StockOverview;
    timeSeries?: TimeSeries;
    globalQuote?: GlobalQuote;
    addedAt?: string;
}
