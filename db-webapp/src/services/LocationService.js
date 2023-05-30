import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const locationAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

locationAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const LocationService = {
    createLocation: async (data) => {
        try {
            const response = await locationAPI.post('/locations', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create a location');
        }
    },

    getLocation: async (locationId) => {
        try {
            const response = await locationAPI.get(`/locations/${locationId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get a location');
        }
    },

    updateLocation: async (data) => {
        try {
            const response = await locationAPI.put(`/locations/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update a location');
        }
    },

    deleteLocation: async (licenceId) => {
        try {
            await locationAPI.delete(`/locations/${licenceId}`);
        } catch (error) {
            throw new Error('Failed to delete a location');
        }
    },

    getAllLocations: async (page, size) => {
        try {
            const response = await locationAPI.get('/locations');

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all locations');
        }
    },

};
export default LocationService;
