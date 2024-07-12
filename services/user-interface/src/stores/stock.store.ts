import { create } from "zustand"

import {Stock} from "./Stock";

import {stocksMockData} from "./__MOCK__/stock.mock";

type State = {
    myStocks: Stock[]
    selectedStock: Stock | null
}

type Action = {
    fetchStocks(): void
    setSelectedStock(symbol: string): void
}

export const useStore = create<State & Action>((set) => ({
    myStocks: [],
    selectedStock: null,
    setSelectedStock(symbol: string) {
        // TODO: replace with real call to backend
        setTimeout(() => {
            const stock = stocksMockData
                .find(el => el.symbol.toLocaleLowerCase() === symbol.toLocaleLowerCase())!;

            set((state) => ({
                ...state,
                selectedStock: stock
            }))
        }, 1500)

    },
    fetchStocks() {
        setTimeout(() => {
            set((state) => ({ ...state, myStocks: stocksMockData }))
        }, 1000)
    }
}))
