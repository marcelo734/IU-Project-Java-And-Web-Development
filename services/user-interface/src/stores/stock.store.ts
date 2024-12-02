import { create } from "zustand"
import axios from 'axios'

import {Stock} from "../types/Stock";

import {stocksMockData} from "./__MOCK__/stock.mock";

const apiUrl =  process.env.REACT_APP_API_URL;
const useMockData = Boolean(process.env.REACT_APP_USE_MOCK_DATA);

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
    fetchStocks() {
        setTimeout(() => {
            set((state) => ({ ...state, myStocks: stocksMockData }))
        }, 1000)
    }
}))
