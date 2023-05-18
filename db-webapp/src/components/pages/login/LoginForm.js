import React, { useState } from 'react';
import AuthService from '../../../services/AuthService';
import { useNavigate } from 'react-router';
import { toast } from 'react-toastify';
import { Button, TextField, CircularProgress } from '@mui/material';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        setLoading(true);

        try {
            await AuthService.login(username, password);
            navigate('/dash');
        } catch (error) {
            if (error.response && error.response.status === 401) {
                toast.error('Nepareizs lietotājvārds vai parole.');
            } else {
                toast.error(
                    'Neizdevās pieslēgties. Lūdzu mēģiniet vēlreiz vai sazinieties ar administrāciju.'
                );
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}
        >
            <TextField
                type="text"
                id="username"
                label="Lietotājvārds"
                variant="outlined"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                style={{ marginBottom: '1rem', width: '300px' }}
                disabled={loading}
            />
            <TextField
                type="password"
                id="password"
                label="Parole"
                variant="outlined"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                style={{ marginBottom: '1rem', width: '300px' }}
                disabled={loading}
            />
            <Button
                type="submit"
                variant="contained"
                style={{ width: '200px' }}
                disabled={loading}
                sx={{ marginTop: '2rem' }}
            >
                {loading ? (
                    <CircularProgress size={24} color="inherit" />
                ) : (
                    'Pieslēgties'
                )}
            </Button>
        </form>
    );
};

export default LoginForm;
