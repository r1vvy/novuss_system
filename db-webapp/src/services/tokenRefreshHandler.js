import React, { useEffect } from 'react';
import AuthService from './AuthService';
import { toast } from 'react-toastify';

const TokenRefreshHandler = () => {
    useEffect(() => {
        const refreshTimer = setTimeout(() => {
            AuthService.refreshAuthToken().catch((error) => {
            });
        }, AuthService.getRefreshTimeMs());

        return () => {
            clearTimeout(refreshTimer);
        };
    }, []);

    return null;
};

export default TokenRefreshHandler;
