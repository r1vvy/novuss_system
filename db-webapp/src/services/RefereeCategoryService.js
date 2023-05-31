import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const refereeCategoryAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

refereeCategoryAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const RefereeCategoryService = {
    createCategory: async (data) => {
        try {
            const response = await refereeCategoryAPI.post('/referees/categories', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to save referee category');
        }
    },

    getCategory: async (categoryId) => {
        try {
            const response = await refereeCategoryAPI.get(`/referees/categories/${categoryId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get referee category');
        }
    },

    updateCategory: async (data) => {
        try {
            const response = await refereeCategoryAPI.put(`/referees/categories/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update referee category');
        }
    },

    deleteCategory: async (categoryId) => {
        try {
            await refereeCategoryAPI.delete(`/referees/categories/${categoryId}`);
        } catch (error) {
            throw new Error('Failed to delete referee category');
        }
    },

    getAllCategories: async () => {
        try {
            const response = await refereeCategoryAPI.get('/referees/categories');

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all referee categories');
        }
    },

};
export default RefereeCategoryService;
