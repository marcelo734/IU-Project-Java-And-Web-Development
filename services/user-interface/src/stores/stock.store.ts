import { create } from "zustand"
import axios from 'axios'

import {Stock} from "../types/Stock";

import {stocksMockData, userStocksMockData} from "./__MOCK__/stock.mock";
import {
    ApiGetUserStocksSearchHistory,
    UserStocksSearchHistory
} from "../types/UserStocksSearchHistory";

const apiUrl =  process.env.REACT_APP_API_URL;
const useMockData = process.env.REACT_APP_USE_MOCK_DATA === "true";

type State = {
    myStocks: UserStocksSearchHistory[]
    selectedStock: Stock | null
}

type Action = {
    fetchStocks(): void
    setSelectedStock(symbol: string): void,
    resetSelectedStockState(): void
}

export const useStore = create<State & Action>((set) => ({
    myStocks: [],
    selectedStock: null,
    async setSelectedStock(symbol: string) {
        if (useMockData) {
            setTimeout(() => {
                const stock = stocksMockData
                    .find(el => el.symbol.toLocaleLowerCase() === symbol.toLocaleLowerCase())!;

                set((state) => ({
                    ...state,
                    selectedStock: stock
                }))
            }, 1500)

            return
        }

        const stock = await axios.get(`${apiUrl}/stocks/${symbol}`)

        set((state) => ({
            ...state,
            selectedStock: stock.data,
        }))

    },
    async fetchStocks() {
        if (useMockData) {
            setTimeout(() => {
                set((state) => ({ ...state, myStocks: userStocksMockData }))
            }, 1000)

            return
        }

        const stocks = await axios.get<ApiGetUserStocksSearchHistory>(`${apiUrl}/stocks`)

        set((state) => ({ ...state, myStocks: stocks.data.stocks }))
    },
    resetSelectedStockState() {
        set(state => ({...state, selectedStock: null}))
        return;
    }
}))
