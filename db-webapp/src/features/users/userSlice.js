import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    role: null,
};

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        setUserRole: (state, action) => {
            state.role = action.payload;
        },
    },
});

export const { setUserRole } = userSlice.actions;
export default userSlice.reducer;
