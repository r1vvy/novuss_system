import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const personAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

personAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const PeopleService = {
    createPerson: async (personData) => {
        try {
            const response = await personAPI.post('/people', personData);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create person');
        }
    },

    getPerson: async (personId) => {
        try {
            const response = await personAPI.get(`/people/${personId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get person');
        }
    },

    updatePerson: async (personData) => {
        try {
            console.log(personData);
            const response = await personAPI.put(`/people/${personData.id}`, personData);

            return response.data;
        } catch (error) {
            console.error(error);
            throw new Error('Failed to update person');
        }
    },

    deletePerson: async (personId) => {
        try {
            await personAPI.delete(`/people/${personId}`);
        } catch (error) {
            throw new Error('Failed to delete person');
        }
    },

    getAllPeople: async (page, size) => {
        try {
            const limitedSize = Math.min(size, MAX_PAGE_SIZE);

            const response = await personAPI.get('/people', {
                params: {
                    page: page,
                    size: size
                }
            });

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all people');
        }
    },

};
export default PeopleService;
