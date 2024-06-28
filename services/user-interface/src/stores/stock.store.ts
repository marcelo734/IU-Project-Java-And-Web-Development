import { create } from "zustand"

import {Stock} from "./Stock";

import {stocksMockData} from "./__MOCK__/stock.mock";

type StockStore = {
    myStocks: Stock[]
}

export const useStore = create<StockStore>((set) => ({
    myStocks: stocksMockData
}))
