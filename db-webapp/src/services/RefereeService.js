import axios from "axios";
import {MAX_PAGE_SIZE, REST_API_DOMAIN} from "../app/api/apiConfig";
import Cookies from "js-cookie";

const refereeAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

refereeAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const RefereeSerivce = {
    createReferee: async (data) => {
        try {
            const response = await refereeAPI.post('/referees', data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to create referee');
        }
    },

    getReferee: async (refereeId) => {
        try {
            const response = await refereeAPI.get(`/referees/${refereeId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to get referee');
        }
    },

    updateReferee: async (data) => {
        try {
            const response = await refereeAPI.put(`/referees/${data.id}`, data);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update referee');
        }
    },

    updateRefereeCategory: async (refereeId, categoryId) => {
        try {
            const response = await refereeAPI.put(`/referees/${refereeId}/category/${categoryId}`);

            return response.data;
        } catch (error) {
            throw new Error('Failed to update player');
        }
    },


    deleteReferee: async (refereeId) => {
        try {
            await refereeAPI.delete(`/referees/${refereeId}`);
        } catch (error) {
            throw new Error('Failed to delete referee');
        }
    },

    getAllReferees: async (page, size) => {
        try {
            const limitedSize = Math.min(size, MAX_PAGE_SIZE);

            const response = await refereeAPI.get('/referees', {
                params: {
                    page: page,
                    size: size
                }
            });

            return response.data;
        } catch (error) {
            throw new Error('Failed to get all referees');
        }
    },

};
export default RefereeSerivce;