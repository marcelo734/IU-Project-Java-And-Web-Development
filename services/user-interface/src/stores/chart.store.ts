import {create} from "zustand";
import {TimeSeriesData} from "./Stock";
import {type ChartData, ChartDataset} from "chart.js";

type SELECTED_STOCK_CHART_DATA = "bar"

type State = {
    selectedStockChartData?: ChartData<SELECTED_STOCK_CHART_DATA>
}

type Actions = {
    calculateLineChartDataSet(stockSeries: TimeSeriesData): void
}

export const useStore = create<State & Actions>((set) => ({
    calculateLineChartDataSet(stockSeries: TimeSeriesData) {
        const labels: string[] = []
        const datasets: ChartDataset<SELECTED_STOCK_CHART_DATA>[] = []

        for (const [key, value] of Object.entries(stockSeries)) {
            if (!labels.includes(key)) labels.push(key)

            datasets.push(
                {
                    label: key,
                    data: [ value.high, ]
                }
            )
        }

        set((state) => ({
            selectedStockChartData: {
                labels: labels,
                datasets: datasets
            }
        }))
    }
}))
