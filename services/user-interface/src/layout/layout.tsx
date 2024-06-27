import React  from "react";
import { Outlet } from 'react-router-dom';

import TopBar from "./topbar/topbar";

export default function Layout() {
    return (
        <>
            <TopBar />

            <main>
                <Outlet />
            </main>
        </>

    )
}
