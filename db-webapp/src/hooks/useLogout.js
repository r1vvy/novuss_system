import React from 'react';
import AuthService from '../services/AuthService';
import {useNavigate} from "react-router";

const useLogout = () => {
    const navigate = useNavigate();

    const logout = () => {
        AuthService.logout();
        navigate('/');
    };

    return logout;
};

export default useLogout;
