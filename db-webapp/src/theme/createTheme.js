import { createTheme } from '@mui/material/styles';

const theme = createTheme({
    palette: {
        primary: {
            main: '#ec1220', // navbar, selected menu item, etc...
        },
        secondary: {
            main: '#f4747c', // buttons, etc...
            secondary: '#D1BEC1' // this can also be used for buttons, etc...
        },
        tertiary: {
            main: '#E39CA8',
            secondary: '#D1BEC1'
        },
        background: {
            main: '#F3F4F9', // page background
            secondary: '#E39CA8' // button backgrounds, etc
        },
        text: {
            main: '#545151',
            secondary: '#A1A1A2'
        }

    },
    typography: {
        fontFamily: 'Montserrat, Arial, Sans-serif'
    }
});

export default theme;