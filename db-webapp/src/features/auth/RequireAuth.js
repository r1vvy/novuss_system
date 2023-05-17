import React from 'react';
import { Navigate, useLocation, Outlet } from 'react-router-dom';
import AuthService from "../../services/AuthService";

const RequireAuth = ({ allowedRoles }) => {
    const location = useLocation();
    const userRoles = AuthService.getUserRoles();
    if (!AuthService.isAuthenticated()) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    }

    if (
        allowedRoles.length > 0 &&
        (!userRoles || userRoles.length === 0 || !userRoles.some(role => allowedRoles.includes(role)))
    ) {
        return <Navigate to="/access-denied" replace />;
    }

    return <Outlet />;
};

export default RequireAuth;
