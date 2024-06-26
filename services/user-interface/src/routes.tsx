import { createBrowserRouter } from "react-router-dom";
import HomePage from "./pages/home.page";
import React from "react";

export const  router = createBrowserRouter([
    {
        path: '/',
        element: <HomePage />
    }
])
