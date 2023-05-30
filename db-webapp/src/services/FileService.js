import axios from 'axios';
import { REST_API_DOMAIN } from '../app/api/apiConfig';
import Cookies from 'js-cookie';
import { saveAs } from 'file-saver';

const fileAPI = axios.create({
    baseURL: REST_API_DOMAIN,
});

fileAPI.interceptors.request.use((config) => {
    const authToken = Cookies.get('authToken');
    config.headers.Authorization = `Bearer ${authToken}`;

    return config;
});

const FileService = {
    uploadFile: async (fileData, title) => {
        try {
            const formData = new FormData();
            formData.append('file', fileData);
            formData.append('title', title);

            const response = await fileAPI.post('/files/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            });

            return response.data;
        } catch (error) {
            throw new Error('Failed to upload file');
        }
    },

    downloadFile: async (fileId) => {
        try {
            const response = await fileAPI.get(`/files/download/${fileId}`, {
                responseType: 'blob',
            });

            const contentDisposition = response.headers['content-disposition'];
            const filename = contentDisposition
                ? contentDisposition.split('filename=')[1].trim()
                : 'file';

            saveAs(response.data, filename);
        } catch (error) {
            throw new Error('Failed to download file');
        }
    },
};

export default FileService;
