import React, {useEffect, useState} from 'react';
import LoginForm from "./LoginForm";
import { useNavigate } from 'react-router-dom';
import AuthService from "../../../services/AuthService";
import {LinearProgress} from "@mui/material";
import LoadingLinearWrapper from "../../wrappers/LoadingLinearWrapper";

const LoginPage = () => {
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
            <div>
                <h2>Ieeja</h2>
                <LoginForm />
            </div>
        </LoadingLinearWrapper>
    );
};

export default LoginPage;
