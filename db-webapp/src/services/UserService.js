import axios from 'axios';
import { AUTH_API_DOMAIN } from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const userAPI = axios.create({
    baseURL: AUTH_API_DOMAIN,
});

userAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const UserAPI = {
    createUser: async (userData) => {
        try {
            const response = await userAPI.post('/users', userData);
            return response.data;
        } catch (error) {
            throw new Error('Failed to create user');
        }
    },

    getUser: async (userId) => {
        try {
            const response = await userAPI.get(`/users/${userId}`);
            return response.data;
        } catch (error) {
            throw new Error('Failed to get user');
        }
    },

    updateUser: async (userData) => {
        try {
            const response = await userAPI.put(`/users/${userData.id}`, userData);
            console.log(response.data);

            return response.data;
        } catch (error) {
            console.error(error);
            throw new Error('Failed to update user');
        }
    },

    deleteUser: async (userId) => {
        try {
            await userAPI.delete(`/users/${userId}`);
        } catch (error) {
            throw new Error('Failed to delete user');
        }
    },

    getAllUsers: async () => {
        try {
            const response = await userAPI.get('/users/all');
            return response.data;
        } catch (error) {
            throw new Error('Failed to get all users');
        }
    },
};

export default UserAPI;
