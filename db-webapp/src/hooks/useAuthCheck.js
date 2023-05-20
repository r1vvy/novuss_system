import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import authService from '../services/AuthService';
import { toast } from 'react-toastify';

const useAuthCheck = () => {
    const navigate = useNavigate();
    const location = useLocation();

    const handleRefresh = async () => {
        try {
            await authService.refreshAuthToken();
        } catch (error) {
            console.log(error);
            toast.error('Jūsu sesija ir beigusies. Lūdzu, piesakieties vēlreiz.');
            authService.logout();
            navigate('/login');
        }
    };

    useEffect(() => {
        const checkRefreshTime = () => {
            const refreshTime = authService.getRefreshTimeMs();
            console.log('refresh time', refreshTime);

            if (6000 > refreshTime && refreshTime > 0) {
                handleRefresh();
            } else if(refreshTime < 0) {
                navigate('/logout');
            }
        };

        const isAuthenticated = authService.isAuthenticated();
        if (isAuthenticated) {
            checkRefreshTime();
        }
    }, [location.key]);

    return authService.isAuthenticated();
};

export default useAuthCheck;
