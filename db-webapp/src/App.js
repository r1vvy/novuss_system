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
import DashboardPage from "./components/pages/DashboardPage";
import {ThemeProvider} from "@mui/material";
import theme from "./theme/createTheme";
import UserListPage from "./components/pages/user/UserListPage";
import LogoutPage from "./components/pages/LogoutPage";
import {useEffect} from "react";
import authService from "./services/AuthService";

function App() {
    useEffect(() => {
        const refreshTime = authService.getRefreshTime() * 1000; // Convert to milliseconds

        const refreshTimer = setTimeout(async () => {
                await authService.refreshAuthToken();
        }, refreshTime);

        return () => clearTimeout(refreshTimer);
    }, []);

    return (
        <>
            <ThemeProvider theme={theme}>
                <ToastContainer />
                <Routes>
                    <Route path="/" element={<Layout />}>

                        <Route index element={<Public />} />
                        <Route path="login" element={<LoginPage />} />
                        <Route path="logout" element= { <LogoutPage /> } />
                        <Route path="access-denied" element={<AccessDeniedPage />} />

                        <Route element={<RequireAuth allowedRoles={[...Object.values(ROLES)]} />}>

                            <Route path="dash" element={<DashLayout />}>

                                <Route index element={<DashboardPage />} />

                                <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />}>

                                    <Route path="users" element={<UserListPage />} />

                                </Route>

                            </Route>

                        </Route>

                    </Route>
            </Routes>

            </ThemeProvider>
        </>
    );
}

export default App;

