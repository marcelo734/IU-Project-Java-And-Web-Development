import {useParams} from "react-router-dom";

export default function StockDetailPage() {
    const { symbol } = useParams()

    return <>
        <ul>
            <li>Symbol: {symbol?.toUpperCase()}</li>
        </ul>
    </>
}
