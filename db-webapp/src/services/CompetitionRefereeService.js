import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const compRefereeAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

compRefereeAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const CompRefereeService = {
    createCompReferee: async (competitionId, refereeId, data) => {
        try {
            const response = await compRefereeAPI.post('/competitions/'+
                competitionId +
                '/referees/' +
                refereeId,
                data
            );

            return response.data;
        } catch (error) {
            throw new Error('Failed to create competition referee');
        }
    },

    getCompPlayer: async (competitionId, personId) => {
        try {
            const response = await compRefereeAPI.get(`/competitions/${competitionId}/referees/${personId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get person');
        }
    },

    updateCompPlayer: async (data) => {
        try {
            const response = await compRefereeAPI.put(`/competitions/${data.competitionId}/referees/${data.refereeId}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update person');
        }
    },

    deleteCompPlayer: async (competitionId, refereeId) => {
        try {
            await compRefereeAPI.delete(`/competitions/${competitionId}/referees/${refereeId}`);
        } catch (error) {
            throw new Error('Failed to delete competition player');
        }
    },

    getAllCompPlayersByCompId: async (competitionId) => {
        try {
            const response = await compRefereeAPI.get(`/competitions/${competitionId}/referees`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all competition referees');
        }
    },

};
export default CompRefereeService;
