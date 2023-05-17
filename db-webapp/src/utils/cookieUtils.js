import Cookies from "js-cookie";
import axios from "axios";

export const setCookie = (name, value, options) => {
    Cookies.set(name, value, options);
};

export const setAuthHeader = () => {
    const token = Cookies.get('access_token');
    if (token) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    } else {
        delete axios.defaults.headers.common['Authorization'];
    }
};
export const removeCookie = (name) => {
    if (typeof document === 'undefined') {
        return;
    }

    Cookies.remove(name);
};

