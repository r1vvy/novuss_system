import axios from "axios";
import {MAX_PAGE_SIZE, REST_API_DOMAIN} from "../app/api/apiConfig";
import Cookies from "js-cookie";

const playerAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

playerAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const PlayerService = {
    createPlayer: async (data) => {
        try {
            const response = await playerAPI.post('/players', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create player');
        }
    },

    getPlayer: async (playerId) => {
        try {
            const response = await playerAPI.get(`/players/${playerId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get player');
        }
    },

    updatePlayer: async (data) => {
        try {
            const response = await playerAPI.put(`/players/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update player');
        }
    },

    updatePlayerLicence: async (playerId, licenceId) => {
        try {
            const response = await playerAPI.put(`/players/${playerId}/licence/${licenceId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update player');
        }
    },

    updatePlayerClub: async (playerId, clubId) => {
        try {
            const response = await playerAPI.put(`/players/${playerId}/club/${clubId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update player');
        }
    },
    updatePlayerSportsClass: async (playerId, sportsClassId) => {
        try {
            const response = await playerAPI.put(`/players/${playerId}/sports-class/${sportsClassId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update player');
        }
    },

    deletePlayer: async (playerId) => {
        try {
            await playerAPI.delete(`/players/${playerId}`);
        } catch (error) {
            throw new Error('Failed to delete player');
        }
    },

    getAllPlayersByPage: async (page, size) => {
        try {
            const limitedSize = Math.min(size, MAX_PAGE_SIZE);

            const response = await playerAPI.get('/players', {
                params: {
                    page: page,
                    size: size
                }
            });

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all players');
        }
    },

};
export default PlayerService;