import { createBrowserRouter } from "react-router-dom";
import HomePage from "./pages/home.page";
import React from "react";
import Layout from "./layout/layout";

export const  router = createBrowserRouter([
    {
        path: '/',
        element: <Layout />,
        children: [
            { path: "/", element: <HomePage /> }
        ]
    }
])
