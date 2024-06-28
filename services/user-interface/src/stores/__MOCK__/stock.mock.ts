import {Stock, TimeSeriesFrequence} from "../Stock";

export const stocksMockData: Stock[] = [
    {
        symbol: "IBM",
        addedAt: new Date().toLocaleDateString(),
        timeSeries: {
            metaData: {
                frequency: TimeSeriesFrequence.MONTHLY
            },
            series: {
                "2024-05-28": {
                    open: "165.6900",
                    high: "175.4600",
                    low: "162.6200",
                    close: "169.6600",
                    volume: "65653753"
                },
                "2024-04-30": {
                    open: "190.0000",
                    high: "193.2800",
                    low: "165.2605",
                    close: "166.2000",
                    volume: "98297181"
                },
            }
        },
        globalQuote: {
            open: "170.4400",
            high: "171.0850",
            low: "168.6500",
            price: "169.6600",
            volume: "2627584",
            latestTradingDay: "2024-05-28",
            previousClose: "170.8900",
            change: "-1.2300",
            changePercent: "-0.7198%"
        }
    }
]
