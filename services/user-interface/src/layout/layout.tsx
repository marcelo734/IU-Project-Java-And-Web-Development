import React  from "react";
import { Outlet } from 'react-router-dom';

import TopBar from "./topbar/topbar";
import styled from "styled-components";

const Main = styled.main`
    padding: 16px;
    background-color: #f4f6f9;
    min-height: 100vh;
`

export default function Layout() {
    return (
        <>
            <TopBar />

            <Main>
                <Outlet />
            </Main>
        </>

    )
}
