import axios from "axios";
import {MAX_PAGE_SIZE, REST_API_DOMAIN} from "../app/api/apiConfig";
import Cookies from "js-cookie";

const competitionAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

competitionAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const CompetitionService = {
    createCompetition: async (data) => {
        try {
            const response = await competitionAPI.post('/competitions', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create competition');
        }
    },

    getCompetition: async (competitionId) => {
        try {
            const response = await competitionAPI.get(`/competitions/${competitionId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get competition');
        }
    },

    updateCompetition: async (data) => {
        try {
            const response = await competitionAPI.put(`/competitions/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update competition');
        }
    },

    updateCompetitionCategory: async (competitionId, categoryId) => {
        try {
            const response = await competitionAPI.put(`/competitions/${competitionId}/categories/${categoryId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update competition category');
        }
    },

    updateCompetitionLocation: async (playerId, clubId) => {
        try {
            const response = await competitionAPI.put(`/competitions/${playerId}/location/${clubId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update competition location');
        }
    },
    addFileToCompetition: async (competitionId, fileId) => {
        try {
            const response = await competitionAPI.put(`/competitions/${competitionId}/files/${fileId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to add file to competition');
        }
    },

    deleteCompetition: async (competitionId) => {
        try {
            await competitionAPI.delete(`/competitions/${competitionId}`);
        } catch (error) {
            throw new Error('Failed to delete competition');
        }
    },

    getAllCompetitionsByPage: async (page, size) => {
        try {
            const limitedSize = Math.min(size, MAX_PAGE_SIZE);

            const response = await competitionAPI.get('/competitions', {
                params: {
                    page: page,
                    size: size
                }
            });

            return response.data;
        } catch (error) {
            throw new Error(`Failed to get competitions in ${page} page of ${size} size`);
        }
    },

};
export default CompetitionService;