import { Routes, Route } from 'react-router-dom';
import Layout from './components/global/Layout';
import DashLayout from './components/global/dash/DashLayout';
import Public from './components/pages/Public';
import AccessDeniedPage from './components/pages/AccessDeniedPage';
import { ROLES } from './app/roles';
import RequireAuth from './features/auth/RequireAuth';
import LoginPage from "./components/pages/login/LoginPage";
import 'react-toastify/dist/ReactToastify.css';
import {toast, ToastContainer} from "react-toastify";
import DashboardPage from "./components/pages/DashboardPage";
import {ThemeProvider} from "@mui/material";
import theme from "./theme/createTheme";
import UserListPage from "./components/pages/user/UserListPage";
import LogoutPage from "./components/pages/LogoutPage";
import {useEffect, useRef} from "react";
import authService from "./services/AuthService";
import {useNavigate} from "react-router";
import Footer from "./components/global/Footer";
import {ProSidebarProvider} from "react-pro-sidebar";

function App() {
    const navigate = useNavigate();
    const refreshTimerRef = useRef(null);

    useEffect(() => {
        const handleRefresh = async () => {
            try {
                await authService.refreshAuthToken();
            } catch (error) {
                toast.error('Jūsu sesija ir beigusies. Lūdzu, piesakieties vēlreiz.');
                authService.logout();
                navigate('/login');
            }
        };

        const startRefreshTimer = () => {
            const refreshTime = authService.getRefreshTime();

            refreshTimerRef.current = setTimeout(handleRefresh, refreshTime);
        };

        const clearRefreshTimer = () => {
            clearTimeout(refreshTimerRef.current);
        };

        if (authService.isAuthenticated()) {
            startRefreshTimer();
        } else {
            clearRefreshTimer();
        }

        return () => {
            clearRefreshTimer();
        };
    }, [navigate]);

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
            <Footer />
            </ThemeProvider>
        </>
    );
}

export default App;

