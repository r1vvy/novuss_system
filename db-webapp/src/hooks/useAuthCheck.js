import { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import authService from '../services/AuthService';
import { Typography } from '@mui/material';

const useAuthCheck = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const [errorMessage, setErrorMessage] = useState('');

    const handleRefresh = async () => {
        try {
            await authService.refreshAuthToken();
            setRefreshTimer(); // Reset the refresh timer after a successful token refresh
        } catch (error) {
            setErrorMessage('Jūsu sesija ir beigusies. Lūdzu, piesakieties vēlreiz.');
            authService.logout();
            navigate('/login');
        }
    };

    const setRefreshTimer = () => {
        const refreshTime = authService.getRefreshTimeMs();

        if (refreshTime > 0) {
            setTimeout(handleRefresh, refreshTime);
        } else {
            setErrorMessage('Jūsu sesija ir beigusies. Lūdzu, piesakieties vēlreiz.');
            navigate('/logout');
        }
    };

    useEffect(() => {
        const isAuthenticated = authService.isAuthenticated();
        if (isAuthenticated) {
            setRefreshTimer()
        }
    }, [location]);

    return (
        <Typography variant="body2" color="error" align="center" gutterBottom>
            {errorMessage}
        </Typography>
    );
};

export default useAuthCheck;
