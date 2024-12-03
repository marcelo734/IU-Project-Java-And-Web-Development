import {KeyboardEvent, useEffect} from 'react';
import styled from "styled-components";
import DataTable from 'react-data-table-component';
import {useNavigate} from "react-router-dom";

import {useStore as useStockStore} from "../../stores/stock.store";
import {UserStocksSearchHistory} from "../../types/UserStocksSearchHistory";
import moment from "moment";

const SearchStocksInput = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
    margin-bottom: 16px;
`

const columns = [
    {
        name: "Symbol",
        selector: (row: UserStocksSearchHistory) => row.symbol
    },
    {
        name: "Name",
        selector: (row: UserStocksSearchHistory) => row.name
    },
    {
        name: "Added At",
        selector: (row: UserStocksSearchHistory) => moment(row.date).toLocaleString()
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

    const stocks: UserStocksSearchHistory[] = useStockStore((state) => state.myStocks)
    const fetchStocks = useStockStore((state) => state.fetchStocks)

    const onViewStock = (symbol: String) => {
        navigate(`/stock/${symbol}`)
    }

    useEffect(() => {
        fetchStocks()
    }, [fetchStocks]);

    const onSearchStockKeyDownEventHandler = (event: KeyboardEvent<HTMLInputElement>) => {
        if (event.code !== "Enter") return

        const symbol = event.currentTarget.value

        onViewStock(symbol)
    }

    return <>
        <SearchStocksInput>
            <label>Search stocks</label>
            <input
                type="text"
                onKeyDown={e => onSearchStockKeyDownEventHandler(e)}
            />
            <small>
                Type stock symbol and press Enter to search.
            </small>
        </SearchStocksInput>

        <DataTable
            columns={columns}
            data={stocks}
            onRowClicked={row => onViewStock(row.symbol)}
            customStyles={tableCustomStyle}
            selectableRows />
    </>
}
