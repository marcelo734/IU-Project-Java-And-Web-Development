import styled from "styled-components";
import DataTable from 'react-data-table-component';

import {useStore} from "../../stores/stock.store";
import {Stock} from "../../stores/Stock";
import {useNavigate} from "react-router-dom";

const SearchStocksInput = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
    margin-bottom: 16px;
`

const columns = [
    {
        name: "Symbol",
        selector: (row: Stock) => row.symbol
    },
    {
        name: "Added At",
        selector: (row: Stock) => row.addedAt || "-"
    }
]

const tableCustomStyle = {
    rows: {
        style: {
            ":hover": {
                cursor: "pointer"
            }
        }
    }
}

export default function HomePage() {
    const stocks: Stock[] = useStore((state) => state.myStocks)

    const navigate = useNavigate()
    const setSelectedStock = useStore(state => state.setSelectedStock)

    const onViewStock = (stock: Stock) => {
        setSelectedStock(stock)
        navigate(`/stock/${stock.symbol}`)
    }

    return <>
        <SearchStocksInput>
            <label>Search stocks</label>
            <input type="text" />
            <div>
                <button>Add</button>
            </div>
        </SearchStocksInput>

        <DataTable
            columns={columns}
            data={stocks}
            onRowClicked={onViewStock}
            customStyles={tableCustomStyle}
            selectableRows />
    </>
}
