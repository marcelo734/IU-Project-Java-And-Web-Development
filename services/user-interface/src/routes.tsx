import { createBrowserRouter } from "react-router-dom";

import Layout from "./layout/layout";
import HomePage from "./pages/home/home.page";
import StockDetailPage from "./pages/stock-detail/stock-detail.page";

export const  router = createBrowserRouter([
    {
        path: '/',
        element: <Layout />,
        children: [
            { path: "/", element: <HomePage /> },
            { path: "/stock/:symbol", element: <StockDetailPage />}
        ]
    }
])
