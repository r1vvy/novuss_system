import axios from 'axios';
import Cookies from 'js-cookie';
import jwtDecode from 'jwt-decode';
import {AUTH_EXPIRY_TIME_MS} from "../app/cookieConfig";
import apiConfig, {AUTH_API_DOMAIN} from "../app/api/apiConfig";
import {toast} from "react-toastify";


class AuthService {
    api = apiConfig;
    timeoutId = null;

    constructor() {
        this.api.defaults.baseURL = AUTH_API_DOMAIN;
    }

    login = async (username, password) => {
        try {
            const response = await axios.post(`${this.api.defaults.baseURL}/auth/authenticate`, {
                username,
                password,
            });
            const authToken = response.data.token;
            const decodedToken = jwtDecode(authToken);
            const roles = decodedToken.roles;
            const cookieExpiryTime = decodedToken.exp;

            Cookies.set('authToken', authToken, { expires: cookieExpiryTime });
            Cookies.set('userRoles', JSON.stringify(roles), { expires: cookieExpiryTime });
            Cookies.set('authExpiry', cookieExpiryTime, { expires: cookieExpiryTime });


        } catch (error) {
            throw error;
        }
    };

    refreshAuthToken = async () => {
        try {
            const token = Cookies.get('authToken');
            const response = await axios.post(`${this.api.defaults.baseURL}/auth/refresh`,
                { token: token }
            );

            const newAuthToken = response.data.token;
            const decodedToken = jwtDecode(newAuthToken);
            const roles = decodedToken.roles;
            const cookieExpiryTime = decodedToken.exp;

            Cookies.set('authToken', newAuthToken, { expires: cookieExpiryTime });
            Cookies.set('userRoles', JSON.stringify(roles), { expires: cookieExpiryTime });
            Cookies.set('authExpiry', cookieExpiryTime, { expires: cookieExpiryTime });

        } catch (error) {
            throw error;
        }
    };

    logout = () => {
        clearTimeout(this.timeoutId);

        Cookies.remove('authToken');
        Cookies.remove('userRoles');
        Cookies.remove('authExpiry');
    };

    getUserRoles = () => {
        const userRoles = Cookies.get('userRoles');

        return userRoles ? JSON.parse(userRoles) : [];
    };

    isAuthenticated = () => {
        return Cookies.get('authToken');
    };

    getRefreshTimeMs = () => {
        const authToken = Cookies.get('authToken');
        const expirationTimestamp = Cookies.get('authExpiry');
        const userRoles = Cookies.get('userRoles');

        if (authToken && expirationTimestamp && userRoles) {
            const currentTimestamp = Date.now();
            const bufferPeriodMs = 1 * 60 * 1000; // 1 minutes in ms
            const remainingTime = expirationTimestamp * 1000 - currentTimestamp - bufferPeriodMs;

            return remainingTime;
        } else {
            return -1;
        }
    };



}

export default new AuthService();

