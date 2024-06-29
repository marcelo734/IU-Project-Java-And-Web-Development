import { create } from "zustand"

import {Stock} from "./Stock";

import {stocksMockData} from "./__MOCK__/stock.mock";

type State = {
    myStocks: Stock[]
    selectedStock?: Stock
}

type Action = {
    fetchStock(symbol: string): void
    setSelectedStock(stock: Stock): void
}

export const useStore = create<State & Action>((set) => ({
    myStocks: stocksMockData,
    setSelectedStock(selectedStock: Stock) {
        set((state) => ({ selectedStock }))
    },
    fetchStock(symbol: string) {
        // TODO: go to backend and fetch the data
    }
}))
