import axios from 'axios';
import {AUTH_API_DOMAIN, MAX_PAGE_SIZE, REST_API_DOMAIN} from '../app/api/apiConfig';
import Cookies from 'js-cookie';

const compPlayerAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

compPlayerAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const CompPlayerService = {
    createCompPlayer: async (competitionId, playerId, data) => {
        try {
            const response = await compPlayerAPI.post('/competitions/'+
                competitionId +
                '/players/' +
                playerId,
                data
            );

            return response.data;
        } catch (error) {
            throw new Error('Failed to create competition player');
        }
    },

    getCompPlayer: async (competitionId, personId) => {
        try {
            const response = await compPlayerAPI.get(`/competitions/${competitionId}/players/${personId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get competition player');
        }
    },

    updateCompPlayer: async (data) => {
        try {
            const response = await compPlayerAPI.put(`/competitions/${data.competitionId}/players/${data.playerId}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update competition player');
        }
    },

    deleteCompPlayer: async (competitionId, personId) => {
        try {
            await compPlayerAPI.delete(`/competitions/${competitionId}/players/${personId}`);
        } catch (error) {
            throw new Error('Failed to delete competition player');
        }
    },

    getAllCompPlayersByCompId: async (competitionId) => {
        try {
            const response = await compPlayerAPI.get(`/competitions/${competitionId}/players`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all competition players');
        }
    },

};
export default CompPlayerService;
