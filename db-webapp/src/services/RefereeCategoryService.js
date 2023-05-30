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
            throw new Error('Failed to create person');
        }
    },

    getCategory: async (categoryId) => {
        try {
            const response = await refereeCategoryAPI.get(`/referees/categories/${categoryId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get person');
        }
    },

    updateCategory: async (data) => {
        try {
            const response = await refereeCategoryAPI.put(`/referees/categories/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update person');
        }
    },

    deleteCategory: async (categoryId) => {
        try {
            await refereeCategoryAPI.delete(`/referees/categories/${categoryId}`);
        } catch (error) {
            throw new Error('Failed to delete person');
        }
    },

    getAllCategories: async () => {
        try {
            const response = await refereeCategoryAPI.get('/referees/categories');

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all people');
        }
    },

};
export default RefereeCategoryService;
