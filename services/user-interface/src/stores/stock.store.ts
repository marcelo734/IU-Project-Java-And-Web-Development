import { create } from "zustand"

import {Stock} from "./Stock";

type StockStore = {
    myStocks: Stock[]
}

export const useStore = create<StockStore>((set) => ({
    myStocks: []
}))
