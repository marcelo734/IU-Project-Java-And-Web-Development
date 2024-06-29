import { Bar } from "react-chartjs-2"
import {Chart as ChartJS, Tooltip, Legend, BarElement, CategoryScale, LinearScale, Title} from "chart.js";


import {useStore as stockStore} from "../../stores/stock.store";
import {useStore as chartStore} from "../../stores/chart.store";

import { formatInteger, formatCurrency } from "../../utils/int-formaters.util"

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
);

export default function StockDetailPage() {
    const selectedStock = stockStore((state) => state.selectedStock)
    const chartDataSet = chartStore((state) => state.selectedStockChartData)

    return <>
        <h1>{selectedStock?.symbol}</h1>

        <ul>
            {selectedStock?.globalQuote && <>

                <li>
                    Volume: {formatInteger(selectedStock.globalQuote.volume)}
                </li>

                <li>
                    Latest Trading Day: {
                        selectedStock.globalQuote.latestTradingDay.format("L")
                    }
                </li>

                <li>
                    Price: {formatCurrency(selectedStock.globalQuote.price)}
                </li>

                <li>
                    High: {formatCurrency(selectedStock.globalQuote.high)}
                </li>

                <li>
                    Open: {formatCurrency(selectedStock.globalQuote.open)}
                </li>

                <li>
                    Low: {formatCurrency(selectedStock.globalQuote.low)}
                </li>

                <li>
                    Change: {formatCurrency(selectedStock.globalQuote.change)} ({
                        selectedStock.globalQuote.changePercent
                    })
                </li>

                <li>
                    Previous Close: {formatCurrency(selectedStock.globalQuote.previousClose)}
                </li>
            </>}
        </ul>

        {chartDataSet && <Bar
            datasetIdKey='id'
            data={chartDataSet} />}
    </>
}
