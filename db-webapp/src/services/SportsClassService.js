import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const sportsClassAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

sportsClassAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const SportsClassService = {
    createSportsClass: async (data) => {
        try {
            const response = await sportsClassAPI.post('/players/sports-classes', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create person');
        }
    },

    getSportsClass: async (licenceId) => {
        try {
            const response = await sportsClassAPI.get(`/players/sports-classes/${licenceId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get person');
        }
    },

    updateSportsClass: async (data) => {
        try {
            const response = await sportsClassAPI.put(`/players/sports-classes/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update person');
        }
    },

    deleteSportsClass: async (sportsClassId) => {
        try {
            await sportsClassAPI.delete(`/players/sports-classes/${sportsClassId}`);
        } catch (error) {
            throw new Error('Failed to delete person');
        }
    },

    getAllSportsClasses: async () => {
        try {
            const response = await sportsClassAPI.get('/players/sports-classes');

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all people');
        }
    },

};
export default SportsClassService;
