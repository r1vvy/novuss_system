import React from 'react';
import { Box, Grid, Typography } from '@mui/material';
import { useTheme } from '@mui/material/styles';

const DashFooter = () => {
    const theme = useTheme();

    return (
        <footer className="dash-footer" style={{ position: 'fixed', bottom: 0, width: '100%' }}>
            <Box
                sx={{
                    backgroundColor: theme.palette.primary.main,
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    paddingX: 2,
                    paddingY: 1.5,
                }}
            >
                <Grid container spacing={2} alignItems="center">
                    <Grid item xs={4}>
                        <Typography variant="body2" className="dash-footer-text">
                            Tehniskais atbalsts: email@example.com
                        </Typography>
                    </Grid>
                    <Grid item xs={4} container justifyContent="center">
                        <Typography variant="body2" className="dash-footer-title" align="center">
                            LNF 2023
                        </Typography>
                    </Grid>
                    <Grid item xs={4} container justifyContent="flex-end">
                        <Typography variant="body2" className="dash-footer-text">
                            0.0.1 versija
                        </Typography>
                    </Grid>
                </Grid>
            </Box>
        </footer>
    );
};

export default DashFooter;
