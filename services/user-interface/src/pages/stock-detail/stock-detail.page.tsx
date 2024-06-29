import {useEffect} from "react";

import {useStore} from "../../stores/stock.store";
import { formatInteger, formatCurrency } from "../../utils/int-formaters.util"


export default function StockDetailPage() {
    const selectedStock = useStore((state) => state.selectedStock)

    useEffect(() => {
        console.log(selectedStock);
    }, [selectedStock])

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
    </>
}
