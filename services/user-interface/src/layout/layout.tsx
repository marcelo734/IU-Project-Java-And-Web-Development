import React  from "react";
import { Outlet } from 'react-router-dom';

import TopBar from "./topbar/topbar";
import styled from "styled-components";

const Main = styled.main`
    padding: 16px;
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
