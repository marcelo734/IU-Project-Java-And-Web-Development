import {useEffect} from "react";
import styled from "styled-components";
import DataTable from 'react-data-table-component';

import {useStore} from "../../stores/stock.store";
import {Stock} from "../../stores/Stock";

const SearchStocksInput = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
    margin-bottom: 16px;
`

const TableRowActions = styled.div`
    display: flex;
    gap: 16px;
`

export default function HomePage() {
    const stocks: Stock[] = useStore((state) => state.myStocks)

    useEffect(() => {
        console.log("Favorite Stocks list", stocks)
    }, [stocks])

    const columns = [
        {
            name: "Symbol",
            selector: (row: Stock) => row.symbol
        },
        {
            name: "Added At",
            selector: (row: Stock) => row.addedAt || "-"
        },
        {
            cell: () => <TableRowActions>
                <button>View</button>
                <button>Remove</button>
            </TableRowActions>,
            ignoreRowClick: true,
        }
    ]

    return <>
        <SearchStocksInput>
            <label>Search stocks</label>
            <input type="text" />
            <div>
                <button>Add</button>
            </div>
        </SearchStocksInput>

        <div>
            <DataTable columns={columns} data={stocks} />
        </div>
    </>
}
