import React, { useEffect, useState } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import AuthService from "../../services/authService";
import authService from "../../services/authService";

const RequireAuth = ({ allowedRoles }) => {
    const userRoles = authService.getUserRoles();
    const location = useLocation();

    if (!AuthService.isAuthenticated()) {
        return <Navigate to="/login" state={{ from: location }} replace />;
    }

    if (allowedRoles.length > 0 && (!userRoles || userRoles.length === 0 || !userRoles.some((role) => allowedRoles.includes(role)))) {
        console.log("Allowed roles: ", allowedRoles);
        console.log("User roles: ", userRoles);
        return <Navigate to="/access-denied" replace />;
    }

    return null;
};

export default RequireAuth;
