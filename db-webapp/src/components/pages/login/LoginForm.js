import React, { useState } from 'react';
import AuthService from '../../../services/AuthService';
import {useNavigate} from "react-router";
import {toast} from "react-toastify";
import {Button} from "@mui/material";

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            await AuthService.login(username, password);
            navigate('/dash');
        } catch (error) {
            if (error.response && error.response.status === 401) {
                toast.error('Nepareizs lietotājvārds vai parole.');
            } else {
                toast.error('Neizdevās pieslēgties. Lūdzu mēģiniet vēlreiz vai sazinieties ar administrāciju.');
            }
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="username">Lietotājvārds:</label>
                <input
                    type="username"
                    id="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
            </div>
            <Button type="submit" variant="contained">Pieslēgties</Button>
        </form>
    );
};

export default LoginForm;
