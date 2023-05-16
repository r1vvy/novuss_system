
export const setUserRole = (role) => ({
    type: 'SET_USER_ROLE',
    payload: role
});

const userReducer = (state = '', action) => {
    switch (action.type) {
        case 'SET_USER_ROLE':
            return action.payload;
        default:
            return state;
    }
};