import axios from 'axios';
import Cookies from 'js-cookie';
import jwtDecode from 'jwt-decode';
import {AUTH_EXPIRY_TIME_MS} from "../app/cookieConfig";
import apiConfig, {AUTH_API_DOMAIN} from "../app/api/apiConfig";
import configureApiBaseUrl from "../app/api/apiConfig";
import {toast} from "react-toastify";



class AuthService {
    expirationDate = new Date(Date.now() + AUTH_EXPIRY_TIME_MS);
    api = apiConfig;
    timeoutId = null;

    constructor() {
        this.api.defaults.baseURL = AUTH_API_DOMAIN;
    }


    login = async (username, password) => {
        try {
            const response = await axios.post(this.api.defaults.baseURL.toString() + '/auth/authenticate', {
                username,
                password,
            });
            const authToken  = response.data.token;

            // Set the auth token in cookies,
            // bad side is that client sided cookies cannot be with attributes: httpOnly and sameSite.
            // that opens my implementation up for CSRF and XSS attacks.

            Cookies.set('authToken', authToken, { expires: this.expirationDate });

            // Decode JWT to extract user roles
            const { roles } = jwtDecode(authToken);
            Cookies.set('userRoles', JSON.stringify(roles), { expires: this.expirationDate });

            const expiresInMs = AUTH_EXPIRY_TIME_MS;
            this.timeoutId = setTimeout(() => {
                toast.info('Jūsu sesija ir beigusies. Lūdzu, piesakieties vēlreiz.',
                    {
                        autoClose: false
                    });
            }, expiresInMs);

            return roles;
        } catch (error) {
            throw error
        }
    }

    refreshAuthToken = async () => {
        try {
            const authToken = Cookies.get('authToken');
            const response = await this.api.post(`/auth/refresh`, authToken);
            const { newAuthToken } = response.data.token;

            // Update the auth token in cookies
            Cookies.set('authToken', newAuthToken, { expires: this.expirationDate });

            // Decode JWT to extract user roles
            const { roles } = jwtDecode(newAuthToken);
            Cookies.set('userRoles', JSON.stringify(roles), { expires: this.expirationDate });

            return roles;
        } catch (error) {
            throw error;
        }
    }


    logout = () => {
        clearTimeout(this.timeoutId);

        Cookies.remove('authToken');
        Cookies.remove('userRoles');
    }

    getUserRoles = () => {
        const userRoles = Cookies.get('userRoles');

        return userRoles ? JSON.parse(userRoles) : [];
    }

    isAuthenticated = () => {
        const authToken = Cookies.get('authToken');
        if (!authToken) {
            return false; // No authToken present
        }
        return true; // authToken is present and valid
    };

    getRefreshTime = () => {
        const authToken = Cookies.get('authToken');
        if (!authToken) {
            return 0;
        }

        const { exp } = jwtDecode(authToken);
        const currentTime = Date.now() / 1000;
        return (exp - currentTime - 60) * 1000; // Convert to milliseconds and subtract 60 seconds
    };
}

export default new AuthService();
