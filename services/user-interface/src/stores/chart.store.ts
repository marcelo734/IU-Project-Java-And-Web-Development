import {create} from "zustand";
import {TimeSeriesData} from "../types/Stock";
import {type ChartData, ChartDataset} from "chart.js";

type SELECTED_STOCK_CHART_DATA = "line"

enum DATA_TYPES {
    HIGH = 'High',
    LOW = 'Low',
    OPEN = 'Open',
    CLOSE = 'Close'
}

type DataSetConfiguration = {
    [key in DATA_TYPES]: {
        label: string;
        borderColor: string;
    };
};

const dataSetConfiguration: DataSetConfiguration = {
    [DATA_TYPES.HIGH]: {
        label: DATA_TYPES.HIGH.toString(),
        borderColor: '#EE4E4E'
    },
    [DATA_TYPES.LOW]: {
        label: DATA_TYPES.LOW.toString(),
        borderColor: '#FFF455'
    },
    [DATA_TYPES.OPEN]: {
        label: DATA_TYPES.OPEN.toString(),
        borderColor: '#219C90'
    },
    [DATA_TYPES.CLOSE]: {
        label: DATA_TYPES.CLOSE.toString(),
        borderColor: '#FFC700'
    }
}

function initDatasetArray(dataSetConfig: DataSetConfiguration): ChartDataset<SELECTED_STOCK_CHART_DATA>[] {
    const result: ChartDataset<SELECTED_STOCK_CHART_DATA>[] = []

    for (const config of Object.values(dataSetConfig)) {
        result.push({...config, data: []})
    }

    return result;
}

type State = {
    selectedStockTimeSeriesChartDataSet?: ChartData<SELECTED_STOCK_CHART_DATA>
}

type Actions = {
    calculateStockTimeSeriesChartDataSet(stockSeries: TimeSeriesData): void
}

export const useStore = create<State & Actions>((set) => ({
    calculateStockTimeSeriesChartDataSet(stockSeries: TimeSeriesData) {
        const labels: string[] = []
        const datasets: ChartDataset<SELECTED_STOCK_CHART_DATA>[] = initDatasetArray(dataSetConfiguration)

        for (const [key, value] of Object.entries(stockSeries)) {
            if (!labels.includes(key)) labels.push(key)

            datasets.find(el => el.label === DATA_TYPES.HIGH)?.data.push(value.high)
            datasets.find(el => el.label === DATA_TYPES.LOW)?.data.push(value.low)
            datasets.find(el => el.label === DATA_TYPES.OPEN)?.data.push(value.open)
            if (value.close) {
                datasets.find(el => el.label === DATA_TYPES.CLOSE)?.data.push(value.close)
            }
        }

        set((state) => ({
            ...state,
            selectedStockTimeSeriesChartDataSet: {
                labels: labels,
                datasets: datasets,
            }
        }))
    }
}))
