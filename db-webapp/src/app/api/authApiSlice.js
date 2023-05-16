import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'
import {AUTH_API_DOMAIN} from "./config"

export const authApiSlice = createApi({
    baseQuery: fetchBaseQuery ({ baseUrl: AUTH_API_DOMAIN}),
    // for caching user data
    tagTypes: ['User'],
    endpoints: builder => ({})
})