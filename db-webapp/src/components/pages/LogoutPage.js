import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from "../../services/AuthService";

const LogoutPage = () => {
    const navigate = useNavigate();

    useEffect(() => {
        // Check if the user is already authenticated
        if (!AuthService.isAuthenticated() || !AuthService.getUserRoles().length > 0) {
            navigate('/');
        } else {
            AuthService.logout();
            navigate('/');
        }
    }, [navigate]);
};
export default LogoutPage;
