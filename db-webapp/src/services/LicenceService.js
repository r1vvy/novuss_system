import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const licenceAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

licenceAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const LicenceService = {
    createLicence: async (data) => {
        try {
            const response = await licenceAPI.post('/players/licences', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create person');
        }
    },

    getPerson: async (licenceId) => {
        try {
            const response = await licenceAPI.get(`/players/licences/${licenceId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get person');
        }
    },

    updateLicence: async (data) => {
        try {
            const response = await licenceAPI.put(`/players/licences/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update person');
        }
    },

    deleteLicence: async (licenceId) => {
        try {
            await licenceAPI.delete(`/players/licences/${licenceId}`);
        } catch (error) {
            throw new Error('Failed to delete person');
        }
    },

    getAllLicences: async (page, size) => {
        try {
            const response = await licenceAPI.get('/people');

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all people');
        }
    },

};
export default LicenceService;
