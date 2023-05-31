import { Routes, Route } from 'react-router-dom';
import Layout from './components/global/Layout';
import DashLayout from './components/global/dash/DashLayout';
import Public from './components/pages/Public';
import AccessDeniedPage from './components/pages/AccessDeniedPage';
import { ROLES } from './app/roles';
import RequireAuth from './features/auth/RequireAuth';
import LoginPage from "./components/pages/login/LoginPage";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from "react-toastify";
import DashboardPage from "./components/pages/DashboardPage";
import { ThemeProvider } from "@mui/material";
import theme from "./theme/createTheme";
import UserListPage from "./components/pages/user/UserListPage";
import LogoutPage from "./components/pages/LogoutPage";
import Footer from "./components/global/Footer";
import useAuthCheck from "./hooks/useAuthCheck";
import PeopleListPage from "./components/pages/people/PeopleListPage";

function App() {
    const errorMessage = useAuthCheck();

    return (
        <>
            <ThemeProvider theme={theme}>
                    <ToastContainer />
                    <Routes>
                        <Route path="/" element={<Layout />}>

                            <Route index element={<Public />} />
                            <Route path="login" element={<LoginPage errorMessage={errorMessage} />} />
                            <Route path="logout" element={<LogoutPage />} />
                            <Route path="access-denied" element={<AccessDeniedPage />} />

                            <Route element={<RequireAuth allowedRoles={[...Object.values(ROLES)]} />}>
                                <Route path="dash" element={<DashLayout />}>

                                    <Route index element={<DashboardPage />} />

                                    <Route element={<RequireAuth allowedRoles={[ROLES.Admin, ROLES.SuperAdmin]} />}>
                                        <Route path="users" element={<UserListPage />} />
                                    </Route>
                                    <Route element={<RequireAuth allowedRoles={[ROLES.Admin, ROLES.SuperAdmin]} />}>
                                        <Route path="people" element={<PeopleListPage />} />
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
