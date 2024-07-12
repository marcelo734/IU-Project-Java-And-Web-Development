import { useEffect } from 'react';
import styled from "styled-components";
import DataTable from 'react-data-table-component';
import {useNavigate} from "react-router-dom";

import {useStore as useStockStore} from "../../stores/stock.store";
import {Stock} from "../../stores/Stock";

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
    const navigate = useNavigate()

    const stocks: Stock[] = useStockStore((state) => state.myStocks)

    const fetchStocks = useStockStore((state) => state.fetchStocks)

    const onViewStock = (stock: Stock) => {
        navigate(`/stock/${stock.symbol}`)
    }

    useEffect(() => {
        fetchStocks()
    }, [fetchStocks]);

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
