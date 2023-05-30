import React from 'react';
import {Box} from "@mui/system";
import {Typography} from "@mui/material";

const AccessDeniedPage = () => {
    return (
            <Box
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                minHeight="100vh"
                bgcolor="primary.main"
            >
                <Typography variant="h2" component="h1" color="secondary.main" gutterBottom>
                    Piekļuve liegta
                </Typography>
                <Typography variant="body1" color="secondary.main">
                    Jums nav vajadzīgo atļauju, lai piekļūtu šai lapai.
                </Typography>
            </Box>
    );
};

export default AccessDeniedPage;
