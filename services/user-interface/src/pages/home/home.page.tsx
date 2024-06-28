import styled from "styled-components";
import Table from 'react-bootstrap/Table';
import {useStore} from "../../stores/stock.store";
import {useEffect} from "react";

const SearchStocksInput = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
    margin-bottom: 16px;
`

const TableRowActionItemsGroup = styled.td`
    display: flex;
    gap: 16px;
`

export default function HomePage() {
    const stocks = useStore((state) => state.myStocks)

    useEffect(() => {
        console.log("Favorite Stocks list", stocks)
    }, [stocks])

    return <>
        <SearchStocksInput>
            <label>Search stocks</label>
            <input type="text" />
            <div>
                <button>Add</button>
            </div>
        </SearchStocksInput>

        <div>
            <Table striped bordered>
                <thead>
                    <tr>
                        <th>Symbol</th>
                        <th>Added at</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                <tr>
                    <td>IBM</td>
                    <td>{new Date().toISOString()}</td>
                    <TableRowActionItemsGroup>
                        <button>View</button>
                        <button>Remove</button>
                    </TableRowActionItemsGroup>
                </tr>
                </tbody>
            </Table>
        </div>
    </>
}
