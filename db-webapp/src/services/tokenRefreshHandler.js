import React, { useEffect } from 'react';
import AuthService from './AuthService';
import { toast } from 'react-toastify';

const TokenRefreshHandler = () => {
    useEffect(() => {
        const refreshTimer = setTimeout(() => {
            AuthService.refreshAuthToken().catch((error) => {
                console.error('Refresh token failed:', error);
                AuthService.logout();
                toast.error('Jūsu sesija ir beigusies. Lūdzu, piesakieties vēlreiz.');
            });
        }, AuthService.getRefreshTime() * 1000);

        return () => {
            clearTimeout(refreshTimer);
        };
    }, []);

    return null;
};

export default TokenRefreshHandler;
