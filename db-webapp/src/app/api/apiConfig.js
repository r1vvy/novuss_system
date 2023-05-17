import {useNavigate} from "react-router";
import axios from 'axios';
import authService from "../../services/AuthService";
import {toast} from "react-toastify";

export const AUTH_API_DOMAIN = 'http://localhost:8000/api/v1'
export const REST_API_DOMAIN = 'http://localhost:8001/api/v1'

const api = axios.create({
    baseURL: REST_API_DOMAIN,
});

let consecutive401Errors = 0;
const maxConsecutive401Errors = 10;

api.interceptors.response.use(
    (response) => {
        consecutive401Errors = 0;

        return response;
    },
    (error) => {
        if (error.response) {
            const status = error.response.status;

            if (status === 401) {
                // Increment the consecutive 401 errors count
                consecutive401Errors++;

                if (consecutive401Errors >= maxConsecutive401Errors) {
                    authService.logout();
                    toast.error('Jūsu sesija ir beigusies. Lūdzu, pieteikties sistēmā vēlreiz.');
                    return Promise.reject(error);
                }

            } else {
                return Promise.reject(error);
            }
        } else {
            toast.warn('Problēmas ar serveri. Lūdzu, mēģiniet vēlreiz vēlāk.');
        }

        return Promise.reject(error);
    }
);
export default api;
