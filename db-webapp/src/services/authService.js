import axios from 'axios';
import Cookies from 'js-cookie';
import jwtDecode from 'jwt-decode';
import {AUTH_API_DOMAIN} from "../app/api/apiConfig";
import {useNavigate} from "react-router";
import {AUTH_EXPIRY_TIME} from "../app/cookieConfig";


class AuthService {
    login = async (username, password) => {
        try {
            const response = await axios.post(`${AUTH_API_DOMAIN}/auth/authenticate`, {
                username,
                password,
            });
            const authToken  = response.data.token;

            // Set the auth token in cookies,
            // bad side is that client sided cookies cannot be with attributes: httpOnly and sameSite.
            // that opens my implementation up for CSRF and XSS attacks.
            Cookies.set('authToken', authToken, { expires: AUTH_EXPIRY_TIME }); // 15 minutes

            // Decode JWT to extract user roles
            const { roles } = jwtDecode(authToken);
            Cookies.set('userRoles', JSON.stringify(roles), { expires: AUTH_EXPIRY_TIME }); // 15 minutes

            return roles;
        } catch (error) {
            console.log('Login failed:', error);
            throw error;
        }
    }

    refreshAuthToken = async () => {
        try {
            const authToken = Cookies.get('authToken');
            const response = await axios.post(`${AUTH_API_DOMAIN}/auth/refresh`, {
                authToken,
            });
            const { newAuthToken } = response.data;

            // Update the auth token in cookies
            Cookies.set('authToken', newAuthToken, { expires: 15 / (24 * 60) }); // 15 minutes

            // Decode JWT to extract user roles
            const { roles } = jwtDecode(newAuthToken);
            Cookies.set('userRoles', JSON.stringify(roles), { expires: AUTH_EXPIRY_TIME });

            return roles;
        } catch (error) {
            // Handle refresh token error
            console.error('Refresh token failed:', error);
            throw error;
        }
    }

    logout = () => {
        // Remove auth token and user roles from storage
        Cookies.remove('authToken');
        Cookies.remove('userRoles');
    }

    getUserRoles = () => {
        console.log('Get user roles:', Cookies.get('userRoles'));
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
        return exp - currentTime - 60; // Subtract 60 seconds to account for latency
    }
}

export default new AuthService();
