import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Paper, Typography, Box } from '@mui/material';
import LoginForm from './LoginForm';
import DashFooter from "../../global/dash/DashFooter";
import LoadingLinearWrapper from '../../wrappers/LoadingLinearWrapper';
import AuthService from "../../../services/AuthService";

const LoginPage = ({ errorMessage }) => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Check if the user is already authenticated
        if (AuthService.isAuthenticated()) {
            // Get user roles from cookies
            const userRoles = AuthService.getUserRoles();

            if (userRoles.length > 0) {
                // User is authenticated and has roles, navigate to the dashboard
                navigate('/dash');
            }
        }
        setLoading(false);
    }, [navigate]);

    return (
        <LoadingLinearWrapper isLoading={loading}>
            <Box
                display="flex"
                alignItems="center"
                justifyContent="center"
                minHeight="100vh"
            >
                <Paper elevation={3} sx={{ padding: '2rem', maxWidth: '400px' }}>
                    <Typography variant="h4" align="center" gutterBottom sx={{ marginBottom: '2rem' }}>
                        Ieeja
                    </Typography>
                    {errorMessage && (
                        <Typography
                            variant="body2"
                            color="error"
                            align="center"
                            gutterBottom
                        >
                            {errorMessage}
                        </Typography>
                    )}
                    <LoginForm />
                </Paper>
            </Box>
        </LoadingLinearWrapper>
    );
};

export default LoginPage;
