import React, { useEffect, useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import DashLayout from './components/dash/DashLayout';
import Public from './components/Public';
import AccessDeniedPage from './components/pages/AccessDeniedPage';
import { ROLES } from './app/roles';
import RequireAuth from './features/auth/RequireAuth';
import LoginPage from "./components/pages/login/LoginPage";

import 'react-toastify/dist/ReactToastify.css';
import {ToastContainer} from "react-toastify";

function App() {
    return (
        <>
            <ToastContainer />
            <Routes>
                <Route path="/*" element={<Layout />}>
                    <Route index element={<Public />} />
                    <Route path="login" element={<LoginPage />} />
                    <Route path="access-denied" element={<AccessDeniedPage />} />

                    <Route
                        element={<RequireAuth allowedRoles={[ROLES.EventManager, ROLES.Admin, ROLES.DEV, ROLES.SUPER_ADMIN]} />}
                    >
                        <Route path="dash" element={<DashLayout />} />
                    </Route>
                </Route>
            </Routes>
        </>
    );
}

export default App;
