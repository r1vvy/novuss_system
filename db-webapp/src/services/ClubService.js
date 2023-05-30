import axios from "axios";
import {MAX_PAGE_SIZE, REST_API_DOMAIN} from "../app/api/apiConfig";
import Cookies from "js-cookie";

const clubAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

clubAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const ClubService = {
    createClub: async (data) => {
        try {
            const response = await clubAPI.post('/clubs', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create club');
        }
    },

    getClub: async (clubId) => {
        try {
            const response = await clubAPI.get(`/clubs/${clubId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get club');
        }
    },

    updateClub: async (data) => {
        try {
            const response = await clubAPI.put(`/clubs/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update club');
        }
    },

    updateLocation: async (clubId, locationId) => {
        try {
            const response = await clubAPI.put(`/clubs/${clubId}/location/${locationId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update club location');
        }
    },

    updateClubContactPerson: async (clubId, personId) => {
        try {
            const response = await clubAPI.put(`/clubs/${clubId}/contact-person/${personId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update club');
        }
    },

    deleteClub: async (clubId) => {
        try {
            await clubAPI.delete(`/clubs/${clubId}`);
        } catch (error) {
            throw new Error('Failed to delete club');
        }
    },

    getAllClubsByPage: async (page, size) => {
        try {
            const limitedSize = Math.min(size, MAX_PAGE_SIZE);

            const response = await clubAPI.get('/clubs', {
                params: {
                    page: page,
                    size: size
                }
            });

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all clubs by page');
        }
    },

};
export default ClubService;