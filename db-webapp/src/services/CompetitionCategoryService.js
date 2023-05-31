
import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const compCategoryAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

compCategoryAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const CompCategoryService = {
    createCategory: async (data) => {
        try {
            const response = await compCategoryAPI.post('/competitions/categories', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to save competition category');
        }
    },

    getCategory: async (categoryId) => {
        try {
            const response = await compCategoryAPI.get(`/competitions/categories/${categoryId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get competition category');
        }
    },

    updateCategory: async (data) => {
        try {
            const response = await compCategoryAPI.put(`/competitions/categories/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update competition category');
        }
    },

    deleteCategory: async (categoryId) => {
        try {
            await compCategoryAPI.delete(`/competitions/categories/${categoryId}`);
        } catch (error) {
            throw new Error('Failed to delete competition category');
        }
    },

    getAllCategories: async () => {
        try {
            const response = await compCategoryAPI.get('/competitions/categories');

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all competition categories');
        }
    },

};
export default CompCategoryService;
