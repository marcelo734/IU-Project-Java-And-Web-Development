import {Line} from "react-chartjs-2"
import {useEffect} from "react";
import {useParams} from "react-router-dom";
import styled from "styled-components";
import {
    Chart as ChartJS,
    Tooltip,
    Legend,
    CategoryScale,
    LinearScale,
    Title,
    PointElement,
    LineElement
} from "chart.js";

import {useStore as stockStore} from "../../stores/stock.store";
import {useStore as chartStore} from "../../stores/chart.store";

import { formatInteger, formatCurrency } from "../../utils/int-formaters.util";
import {Card} from "../../components/Card";
import Loading from "../../components/Loading";

ChartJS.register(
    CategoryScale,
    LinearScale,
    Title,
    Tooltip,
    Legend,
    PointElement,
    LineElement,
);


const UnstyledList = styled.ul`
    list-style: none;
    padding: 0;
`;

export default function StockDetailPage() {
    const symbol = useParams().symbol!

    const selectedStock = stockStore((state) => state.selectedStock)
    const chartDataSet = chartStore((state) => state.selectedStockTimeSeriesChartDataSet)

    const setSelectedStock = stockStore((state) => state.setSelectedStock)
    const calculateStockTimeSeriesChartDataSet = chartStore((state) => state.calculateStockTimeSeriesChartDataSet)

    useEffect(() => {
        setSelectedStock(symbol)
    }, [symbol, setSelectedStock])

    useEffect(() => {
        if (selectedStock && selectedStock.timeSeries) {
            calculateStockTimeSeriesChartDataSet(selectedStock.timeSeries.series)
        }
    }, [selectedStock, calculateStockTimeSeriesChartDataSet]);

    if (!selectedStock) return <Loading />

    return <>

        <div className="row">
            <div className="col-12">
                <Card>
                    <h1>{selectedStock?.overview.name} ({selectedStock?.symbol})</h1>

                    <p>{selectedStock?.overview.sector} - {selectedStock?.overview.industry} - {selectedStock?.overview.country}</p>
                    <p>Exchange: {selectedStock?.overview.exchange} - Currency: {selectedStock?.overview.currency}</p>

                        <p>{selectedStock?.overview.description}</p>
                </Card>
            </div>
        </div>

        <div className="row">
            <div className="col-6">
                <Card>
                    <h2>{selectedStock?.timeSeries?.metaData.frequency} TimeSeries</h2>

                    {chartDataSet && <Line
                        datasetIdKey='id'
                        data={chartDataSet}/>}
                </Card>
            </div>

            <div className="col-6">
                <Card>
                    <h2>Stock Trade Overview</h2>

                    <UnstyledList>
                        {selectedStock?.globalQuote && <>
                            <li>
                                <b>Latest Trading Day</b>: {
                                selectedStock.globalQuote.latestTradingDay.format("L")
                            }
                            </li>

                            <li>
                                <b>Volume</b>: {formatInteger(selectedStock.globalQuote.volume)}
                            </li>

                            <li>
                                <b>Price</b>: {formatCurrency(selectedStock.globalQuote.price)}
                            </li>

                            <li>
                                <b>High</b>: {formatCurrency(selectedStock.globalQuote.high)}
                            </li>

                            <li>
                                <b>Open</b>: {formatCurrency(selectedStock.globalQuote.open)}
                            </li>

                            <li>
                                <b>Low</b>: {formatCurrency(selectedStock.globalQuote.low)}
                            </li>

                            <li>
                                <b>Change</b>: {formatCurrency(selectedStock.globalQuote.change)} ({
                                selectedStock.globalQuote.changePercent
                            })
                            </li>

                            <li>
                                <b>Previous Close</b>: {formatCurrency(selectedStock.globalQuote.previousClose)}
                            </li>
                        </>}
                    </UnstyledList>
                </Card>
            </div>
        </div>
    </>
}
