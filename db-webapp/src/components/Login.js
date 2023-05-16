import React, {useEffect, useRef, useState} from 'react';
import {useDispatch} from "react-redux";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import {AUTH_API_DOMAIN} from "../app/api/config";
import {setCookie} from "../utils/cookieUtils";
import {setUserRole} from "../features/users/userActions";


const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false)
    const [error, setError] = useState('');

    const navigate = useNavigate();
    const dispatch = useDispatch();

    const usernameInputRef = useRef(null);

    useEffect(() => {
        usernameInputRef.current.focus();
    }, []);

    const handleLogin = async () => {
        // TODO: dispatch login action
        if (!username || !password) {
            setError('Lūdzu, ievadiet lietotājvārdu un/vai paroli!');
            return;
        }

        try {
            console.log(username, password)
            const response = await axios.post(
                AUTH_API_DOMAIN + '/auth/authenticate',
                { username, password }
            )
            const userData = response.data;
            dispatch(setUserRole(userData.role));

            setCookie('access_token', userData.token, { expires: 15 * 60 });

            navigate('/dash');
        } catch (error) {
            console.log(error)
            setError('Autorizācija neizdevās! Lūdzu mēģiniet vēlreiz vai sazinieties ar administratoru!');
            setPassword('');
        }
    };

    const handleFormSubmit = (e) => {
        e.preventDefault();
        handleLogin();
    }

    return (
            <div className="login-form">
                <h2>Ieeja</h2>
                <form onSubmit={handleFormSubmit}>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Lietotājvārds</label>
                        <input
                            id="username"
                            className={username.length > 0 ? 'form-control' : 'form-control is-invalid'}
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            ref={usernameInputRef}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password">Parole</label>
                        <input
                            id="password"
                            className={password.length > 0 ? 'form-control' : 'form-control is-invalid'}
                            type={showPassword ? 'text' : 'password'}
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-text">
                        <input
                            type="checkbox"
                            id="showPassword"
                            checked={showPassword}
                            onChange={() => setShowPassword(!showPassword)}
                        />
                        <label htmlFor="showPassword">Rādīt paroli</label>
                    </div>
                    <button type="button" className="btn btn-primary" onClick={handleLogin}>Pieslēgties</button>
                </form>
                {error && <div className="alert alert-danger mt-3" role="alert">{error}</div>}
            </div>
    );
}

export default LoginPage;